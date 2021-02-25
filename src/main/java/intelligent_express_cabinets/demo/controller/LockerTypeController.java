package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.LockerType;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.service.ILockerTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/locker-type")
public class LockerTypeController {
    @Autowired
    ILockerTypeService lockerTypeService;

    @ApiOperation(value = "从柜机类型新建柜机")
    @PostMapping("/{lockerTypeId}/createNewLocker")
    public returnBean createNewLocker(@PathVariable Integer lockerTypeId,@RequestParam String longitude,@RequestParam String latitude){
        Lockers lockers=lockerTypeService.createByLockerType(lockerTypeId,longitude,latitude);
        return returnBean.success("创建成功",lockers);

    }

    @ApiOperation(value = "获取所有柜机类型")
    @GetMapping
    public returnBean getAll(){
        return returnBean.success("获取成功",lockerTypeService.list());
    }





    @ApiOperation(value = "新建柜机类型")
    @PostMapping("/add")
    public returnBean addLockerType(@RequestBody LockerType lockerType){
        if (lockerTypeService.save(lockerType)){
            return returnBean.success("新建柜机类型成功");
        }else return returnBean.error("新建柜机类型成功");
    }

    @ApiOperation(value = "修改柜机类型")
    @PostMapping("/update")
    public returnBean updateLockerType(@RequestBody LockerType lockerType){
        if (lockerTypeService.updateById(lockerType)){
            return returnBean.success("修改成功");
        }else return returnBean.error("修改失败");
    }

    @ApiOperation(value = "根据id删除柜机类型")
    @DeleteMapping("/delete/{lockerTypeId}")
    public returnBean delete(@PathVariable Integer lockerTypeId){
        if (lockerTypeService.removeById(lockerTypeId)){
            return returnBean.success("删除成功");
        }else return returnBean.error("删除失败");
    }



}
