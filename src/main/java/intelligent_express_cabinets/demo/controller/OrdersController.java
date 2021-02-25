package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.*;
import intelligent_express_cabinets.demo.service.*;
import intelligent_express_cabinets.demo.util.GenerateSequenceUtil;
import intelligent_express_cabinets.demo.util.GouldUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/orders/basic")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @Resource
    private IUsersService usersService;

    @Resource
    private IBoxesService boxesService;

    @Resource
    private ICodesService codesService;

    @Resource
    private ILockersService lockersService;

    @Resource
    private ILockerTypeService lockerTypeService;

    @Autowired
    private GouldUtil gouldUtil;

    //存取货物码
    private Integer code;

    @ApiOperation(value = "新建订单")
    @PostMapping("/add")
    public returnBean addOrders(@RequestBody Orders orders , Principal principal){
        //新建创建时间
        orders.setOrderStartTime(LocalDateTime.now());
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        Integer userId = users.getUserId();
        orders.setUserId(userId);
        //订单类型:1.会员寄件 2.快递员派件
        orders.setOrderType(1);
        //订单状态:1.待存件 2.待取件 3.完成 4.已取消 5.订单不可见（会员删除，管理员可见）0.注销
        orders.setOrderStatus(1);
        Codes codes = codesService.getByMaxId();
        code = codes.getCodeId();
        code = code + 1;
        /*
        * （这里应该是寻找可用的code，没有code才新建吧）
        *
        * */

        orders.setOrderCode(code);
        if (ordersService.save(orders)){
            Map<String,Integer> orderMap = new HashMap<>();
            orderMap.put("code",code);
            return returnBean.success("新建订单成功!",orderMap);
        }
        return returnBean.error("新建订单失败!");
    }


    @ApiOperation(value = "确认存储货物")
    @GetMapping("/thing")
    public returnBean getThingInfo(){
        Integer index = 1;
        Integer boxesId = 0;
        Integer locker_boxId = 0;
        Integer lockerId = 0;
        Orders orders = ordersService.getOrderByCode(code);
        String address = orders.getSendAddress();
        Integer boxSize = orders.getBoxSize();
        Lockers lockers1 = new Lockers();
        try {
            List<Lockers> lockers = lockersService.getLockerBoxes(boxSize);
            for (Lockers t : lockers) {
                try {
                    String addr = gouldUtil.getAMapByLngAndLat(t.getLongitude(), t.getLatitude());
                    Long longSize = gouldUtil.getDistanceByAddress(address,addr);
//                    if (longSize==1 && t.getBoxes().get(0).getBoxStatus()==1){
//                        //找到对应的快递柜
//                        lockers1=t;
//                    }
                      if (longSize==1){
                        //找到对应的快递柜
                          lockers1=t;
                      }
                } catch (Exception e) {
                    e.printStackTrace();
                    return returnBean.error("筛选柜子失败！");
                }
            }
            //在柜子列表中寻找符合条件的其中一个柜子
            lockerId = lockers1.getLockerId();
            for (Boxes b : lockers1.getBoxes()){
                if ( b.getBoxStatus()==1 ){
                    boxesId = b.getBoxId();
                    locker_boxId = b.getLockerBoxId();
                }
            }
            //更改对应快递柜中符合条件的柜子的状态（使用中（正常））
            Boxes boxes = new Boxes();
            boxes.setBoxId(boxesId);
            boxes.setBoxStatus(2);
            boxesService.updateById(boxes);

            //存储码绑定
            Codes codes = new Codes();
            codes.setCodeId(code);
            codes.setBoxId(locker_boxId);
            codes.setLockerId(lockerId);
            codes.setCodeStatus(2);
            codes.setOrderId(orders.getOrderId());
            codesService.save(codes);

                 //修改一下订单消息
            orders.setOrderStatus(2);
                 //模拟快递公司输入快递员信息
            orders.setExpressmanTel(GenerateSequenceUtil.generateSequenceNo());
            orders.setExpressNumber("15847965326");
            ordersService.updateById(orders);


            //修改一下快递柜的柜子数量
            Lockers lockers2 = lockersService.getById(lockerId);
            if (orders.getBoxSize()==1 && lockers2.getBigBoxAbleNumber()!=0){
                lockers2.setBigBoxAbleNumber(lockers2.getBigBoxAbleNumber()-1);
                index = 0;
                lockersService.updateById(lockers2);
            }
            else if (orders.getBoxSize()==2 && lockers2.getMiddleBoxAbleNumber()!=0){
                lockers2.setMiddleBoxAbleNumber(lockers2.getMiddleBoxAbleNumber()-1);
                index = 0;
                lockersService.updateById(lockers2);
            }
            else if (orders.getBoxSize()==3 && lockers2.getSmallBoxAbleNumber()!=0){
                lockers2.setSmallBoxAbleNumber(lockers2.getSmallBoxAbleNumber()-1);
                index = 0;
                lockersService.updateById(lockers2);
            }

            if(index==0){
                Map<String,Integer> boxMap = new HashMap<>();
                boxMap.put("boxId",locker_boxId);
                return returnBean.success("返回符合条件的柜子",boxMap);
            } else{
                return returnBean.error("柜子已经存储满了!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return returnBean.error("存储货物失败!");
        }
    }

    @ApiOperation(value = "获取当前会员的的所有订单信息")
    @GetMapping("/member/orders")
    public returnBean getOrders(Principal principal){
        if(null==principal){
            return null;
        }
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        List<Orders> orders = ordersService.getOrdersByUserId(users.getUserId());
        return returnBean.success("获取所有订单信息成功!",orders);
    }

    @ApiOperation(value = "根据id获取详细的订单信息")
    @PostMapping("/get/orders/{orderId}")/*这里应该是用GET吧*/
    public returnBean addOrders(@PathVariable Integer orderId){
        Orders order = ordersService.getOrderByUserId(orderId);
        return returnBean.success("获取详细订单信息成功!",order);
    }

    @ApiOperation(value = "根据id删除订单信息")
    @DeleteMapping("/delete/{orderId}")
    public returnBean deleteOrder(@PathVariable Integer orderId){
        Orders order = ordersService.getById(orderId);
        order.setOrderStatus(5);
        ordersService.updateById(order);
        return returnBean.success("删除订单信息成功!");
    }

    @ApiOperation(value = "取消订单")
    @PostMapping("/cancel/orders/{orderId}")
    public returnBean cancelOrders(@PathVariable Integer orderId){
        Orders order = ordersService.getById(orderId);
        Integer boxSize = order.getBoxSize();
        //设置订单(为已取消状态)
        order.setOrderStatus(4);
        order.setOrderEndTime(LocalDateTime.now());
        ordersService.updateById(order);

        Codes codes = codesService.getCodeByOrderId(orderId);
        Integer lockerId = codes.getLockerId();
        //设置柜机中柜子的状态为1:未使用（正常）
        Integer boxId = codes.getBoxId();
        Boxes boxes = boxesService.getByLockerIdLockerBoxId(lockerId,boxId);
        boxes.setBoxStatus(1);
        boxesService.updateById(boxes);

        //设置存储码为0:(状态为解绑)
        codes.setCodeStatus(0);
        codesService.updateById(codes);
        Lockers lockers = lockersService.getById(lockerId);
        Integer locker_type_id = lockers.getLockerTypeId();
        LockerType lockerType = lockerTypeService.getById(locker_type_id);
        Integer bigNum = lockerType.getBigBoxNumber();
        Integer minNum = lockerType.getMiddleBoxNumber();
        Integer smaNum = lockerType.getSmallBoxNumber();
        if (boxSize == 1 && lockers.getBigBoxAbleNumber() < bigNum){
            lockers.setBigBoxAbleNumber(lockers.getBigBoxAbleNumber()+1);
            lockersService.updateById(lockers);
        }
        else if (boxSize == 2 && lockers.getMiddleBoxAbleNumber() < minNum){
            lockers.setMiddleBoxAbleNumber(lockers.getMiddleBoxAbleNumber()+1);
            lockersService.updateById(lockers);
        }
        else if (boxSize == 3 && lockers.getSmallBoxAbleNumber() < smaNum){
            lockers.setSmallBoxAbleNumber(lockers.getSmallBoxAbleNumber()+1);
            lockersService.updateById(lockers);
        }
        return returnBean.success("取消订单成功!");
    }

    @ApiOperation(value = "已经取件")
    @PostMapping("/finish/orders/{orderId}")
    public returnBean finishOrders(@PathVariable Integer orderId){
        Orders order = ordersService.getById(orderId);
        Integer boxSize = order.getBoxSize();
        //设置订单(为已完成状态)
        order.setOrderStatus(3);
        order.setOrderEndTime(LocalDateTime.now());
        ordersService.updateById(order);

        Codes codes = codesService.getCodeByOrderId(orderId);
        Integer lockerId = codes.getLockerId();
        //设置柜机中柜子的状态为1:未使用（正常）
        Integer boxId = codes.getBoxId();
        Boxes boxes = boxesService.getByLockerIdLockerBoxId(lockerId,boxId);
        boxes.setBoxStatus(1);
        boxesService.updateById(boxes);

        //设置存储码为0:(状态为解绑)
        codes.setCodeStatus(0);
        codesService.updateById(codes);
        Lockers lockers = lockersService.getById(lockerId);
        Integer locker_type_id = lockers.getLockerTypeId();
        LockerType lockerType = lockerTypeService.getById(locker_type_id);
        Integer bigNum = lockerType.getBigBoxNumber();
        Integer minNum = lockerType.getMiddleBoxNumber();
        Integer smaNum = lockerType.getSmallBoxNumber();

        if (boxSize == 1 && lockers.getBigBoxAbleNumber() < bigNum){
            lockers.setBigBoxAbleNumber(lockers.getBigBoxAbleNumber()+1);
            lockersService.updateById(lockers);
        }
        else if (boxSize == 2 && lockers.getMiddleBoxAbleNumber() < minNum){
            lockers.setMiddleBoxAbleNumber(lockers.getMiddleBoxAbleNumber()+1);
            lockersService.updateById(lockers);
        }
        else if (boxSize == 3 && lockers.getSmallBoxAbleNumber() < smaNum){
            lockers.setSmallBoxAbleNumber(lockers.getSmallBoxAbleNumber()+1);
            lockersService.updateById(lockers);
        }
        return returnBean.success("已经取件成功!");
    }

}
