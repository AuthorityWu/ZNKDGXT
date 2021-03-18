package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.service.IBoxesService;
import intelligent_express_cabinets.demo.service.ICodesService;
import intelligent_express_cabinets.demo.service.ILockersService;
import intelligent_express_cabinets.demo.service.IOrdersService;
import intelligent_express_cabinets.demo.util.GenerateSequenceUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/lockers")
public class LockersController {
    @Autowired
    IBoxesService boxesService;
@Autowired
    ICodesService codesService;
@Autowired
    ILockersService lockersService;
@Autowired
    IOrdersService ordersService;
@Autowired
    OrdersController ordersController;

    @ApiOperation("获取所有柜机")
    @GetMapping("")
    public returnBean getAll(){
        List<Lockers> lockersList=lockersService.list();
        return returnBean.success("获取成功",lockersList);
    }



    @ApiOperation("获取柜机的所有柜子")
    @GetMapping("/boxes/{lockerId}")
    public returnBean getBoxes(@PathVariable Integer lockerId){
        try {
            List<Boxes> boxesList = lockersService.getBoxByLockerId(lockerId);
            return returnBean.success("成功",boxesList);
        }catch (Exception e){
            return returnBean.error("此id的柜机不存在");
        }

    }

    @ApiOperation("存件(执行码柜绑定，返回柜机和其中柜子编号)")
    @PostMapping("/store")
    public returnBean store(@RequestParam Integer lockerId, @RequestParam Integer codeId){
        boolean isExistBoxCanUse = false;
        Integer boxesId = 0;
        Integer locker_boxId = 0;

        Codes codes=codesService.getById(codeId);
        if (codes==null)
            return returnBean.error("码不存在");
        Orders orders = ordersService.getOrderByCode(codeId);
        if(orders==null)
            return returnBean.error("码尚未绑定订单");
        Lockers lockers2 = lockersService.getById(lockerId);
        if (lockers2==null)
            return returnBean.error("此柜机不存在");
        if (lockers2.getLockerStatus()!=1)
            return returnBean.error("此柜机不能存件");

        //在柜子列表中寻找符合条件的其中一个柜子
        try {
            for (Boxes b : lockersService.getBoxByLockerId(lockerId)){
                if ( b.getBoxStatus()==1 && b.getBoxSize().equals(orders.getBoxSize())){
                    boxesId = b.getBoxId();
                    locker_boxId = b.getLockerBoxId();
                    isExistBoxCanUse=true;
                }
            }
            if (!isExistBoxCanUse) {
                return returnBean.error("没有合适的柜子储存!");
            }
            //更改对应快递柜中符合条件的柜子的状态（使用中（正常））
            Boxes boxes = new Boxes();
            boxes.setBoxId(boxesId);
            boxes.setBoxStatus(2);
            boxesService.updateById(boxes);

            //存储码绑定
            //Codes codes=codesService.getById(codeId);

            codes.setLockerId(lockerId);
            codes.setBoxId(locker_boxId);
            //绑定柜子(3)
            codes.setCodeStatus(3);
            codesService.updateById(codes);


            //修改一下订单消息
            orders.setOrderStatus(2);
            //模拟快递公司输入快递员信息
            orders.setExpressmanTel("15847965326");
            orders.setExpressNumber(GenerateSequenceUtil.generateSequenceNo());
            ordersService.updateById(orders);


            //修改一下快递柜的柜子数量


            if (orders.getBoxSize()==1 && lockers2.getBigBoxAbleNumber()!=0){
                lockers2.setBigBoxAbleNumber(lockers2.getBigBoxAbleNumber()-1);
                //isExistBoxCanUse = 0;
                lockersService.updateById(lockers2);
            }
            else if (orders.getBoxSize()==2 && lockers2.getMiddleBoxAbleNumber()!=0){
                lockers2.setMiddleBoxAbleNumber(lockers2.getMiddleBoxAbleNumber()-1);
                //isExistBoxCanUse = 0;
                lockersService.updateById(lockers2);
            }
            else if (orders.getBoxSize()==3 && lockers2.getSmallBoxAbleNumber()!=0){
                lockers2.setSmallBoxAbleNumber(lockers2.getSmallBoxAbleNumber()-1);
                //isExistBoxCanUse = 0;
                lockersService.updateById(lockers2);
            }


            Map<String,Integer> boxMap = new HashMap<>();
            boxMap.put("lockerId",lockerId);
            boxMap.put("boxId",locker_boxId);
            return returnBean.success("返回符合条件的柜子",boxMap);


        } catch (Exception e) {
            e.printStackTrace();
            return returnBean.error("存储货物失败!");
        }
    }



    @ApiOperation(value = "取件(码柜解除绑定,调用完成订单操作)",notes ="会自动调用完成订单操作" )
    @PostMapping("/pickup")
    public returnBean pickup(@RequestParam Integer lockerId, @RequestParam Integer codeId){
        Codes codes=codesService.getById(codeId);
        if (codes.getCodeStatus()!=3){
            return returnBean.error("此码尚未使用");
        }
        if(!lockerId.equals(codes.getLockerId())){
            return returnBean.error("此柜机不是此码绑定柜子的柜机");
        }
        Orders orders= ordersService.getOrderByCode(codeId);

        //Integer lockerId=codes.getLockerId();
        Integer lockerBoxId=codes.getBoxId();
        if(ordersController.finishOrders(orders.getOrderId()).getCode()==200) {
            Boxes boxes = boxesService.getByLockerIdLockerBoxId(lockerId, lockerBoxId);
            //Map<String,Integer> boxMap = new HashMap<>();
            return returnBean.success("成功,返回对应柜子", boxes);
        }else return returnBean.error("失败");
    }

    @ApiOperation(value = "柜子报告损坏",notes = "lockerBoxId是柜机中的柜子ID")
    @PostMapping("/reportBroken")
    public returnBean reportBroken(@RequestParam Integer lockerId,@RequestParam Integer lockerBoxId){
        Boxes boxes=boxesService.getByLockerIdLockerBoxId(lockerId,lockerBoxId);
        //设置不可用（0）
        boxes.setBoxStatus(0);
        if(boxesService.updateById(boxes)) {
            return returnBean.success("成功报告");
        }
        else return returnBean.error("失败");
    }


/*
    @ApiOperation("使用柜子")
    @PostMapping("{lockerId}/boxes/{LockerboxId}/use")
    public returnBean use(@PathVariable Integer lockerId,@PathVariable Integer LockerboxId, @RequestParam Integer codeId){

        Boxes boxes = boxesService.getByLockerIdLockerBoxId(lockerId, LockerboxId);
        // codes=codesService.getById(codeId);

        boxes.setBoxStatus(1);

        boxesService.bindCode(boxes,codes);
        codes.setCodeStatus(2);

        boxesService.updateById(boxes);
        codesService.updateById(codes);

        return returnBean.success("柜子使用成功");
    }*/
/*
    @ApiOperation("柜子使用完毕")
    @PostMapping("{lockerId}/boxes/{LockerboxId}/notUse")
    public returnBean notUse(@PathVariable Integer lockerId,@PathVariable Integer LockerboxId, @RequestParam Integer codeId){

        Boxes boxes = boxesService.getByLockerIdLockerBoxId(lockerId, LockerboxId);
        Codes codes=codesService.getByBox(boxes);

        boxesService.noBindCode(codes);
        codes.setCodeStatus(1);

        boxesService.updateById(boxes);
        codesService.updateById(codes);

        return returnBean.success("柜子使用成功");
    }
*/
    @ApiOperation("设置柜机状态(1正常，2暂停存件)")
    @PostMapping("/setStatus")
    public returnBean pause(@RequestParam Integer lockerId,@RequestParam Integer status){
        Lockers lockers = lockersService.getById(lockerId);
        lockers.setLockerStatus(status);
        if(lockersService.updateById(lockers)){
            return returnBean.success("成功");
        }
        else return returnBean.error("失败");


    }

    @ApiOperation("删除柜机（连带删除柜子）")
    @DeleteMapping("/delete/{lockerId}")
    public returnBean delete(@PathVariable Integer lockerId){
        List<Boxes> boxesList = lockersService.getBoxByLockerId(lockerId);
        boolean isEmpty=true;
        for (Boxes boxes :
                boxesList) {
            if (boxes.getBoxStatus() == 2) {
                isEmpty = false;
                break;
            }
        }
        if (isEmpty){
            lockersService.delete(lockerId);
            return returnBean.success("删除成功");
        }else return returnBean.error("柜机内仍然有物品，不能执行删除操作");
    }
    @ApiOperation("停用柜机")
    @PostMapping("/stop/{lockerId}")
    public returnBean stop(@PathVariable Integer lockerId){
        List<Boxes> boxesList = lockersService.getBoxByLockerId(lockerId);
        boolean isEmpty=true;
        for (Boxes boxes :
                boxesList) {
            if (boxes.getBoxStatus() == 2) {
                isEmpty = false;
                break;
            }
        }
        if (isEmpty){
            Lockers lockers = lockersService.getById(lockerId);
            lockers.setLockerStatus(3);
            lockersService.updateById(lockers);
            //lockersService.delete(lockerId);
            return returnBean.success("停用成功");
        }else return returnBean.error("柜机内仍然有物品，不能执行停用操作");
    }
}
