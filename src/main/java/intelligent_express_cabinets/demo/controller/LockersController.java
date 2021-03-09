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
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @ApiOperation(value = "取件(码柜解除绑定)",notes ="会自动调用完成订单操作" )
    @PostMapping("/pickup")
    public returnBean pickup(@RequestParam Integer codeId){
        Orders orders= ordersService.getOrderByCode(codeId);
        return ordersController.finishOrders(orders.getOrderId());
        //Codes codes=codesService.getById(codeId);
        //Boxes boxes=boxesService.getByLockerIdLockerBoxId(codes.getLockerId(),codes.getBoxId());
        //boxes.set
        //codes.setLockerId(0);
        //codes.setCodeStatus(1);
        //codesService.updateById(codes);
        //return returnBean.success("成功");
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
}
