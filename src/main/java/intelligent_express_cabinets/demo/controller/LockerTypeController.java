package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.LockerType;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.service.ILockerTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lockerType")
public class LockerTypeController {
    @Autowired
    ILockerTypeService lockerTypeService;

    @ApiOperation(value = "从柜机类型新建柜机")
    @PostMapping("/createNewLocker")
    public returnBean createNewLocker(@RequestParam Integer lockerTypeId,@RequestParam String longitude,@RequestParam String latitude){
        try {
            Lockers lockers=lockerTypeService.createByLockerType(lockerTypeId,longitude,latitude);
            return returnBean.success("创建成功",lockers);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return returnBean.error("出错");

        }


    }

    @ApiOperation(value = "获取所有柜机类型")
    @GetMapping("")
    public returnBean getAll(){
        return returnBean.success("获取成功",lockerTypeService.list());
    }

    @ApiOperation(value = "通过柜机类型的获取同类柜子")
    @GetMapping("/lockers/{lockerTypeId}")
    public returnBean getLockerByLockerType(@PathVariable Integer lockerTypeId){
        try {
            LockerType lockerType = lockerTypeService.getById(lockerTypeId);
            List<Lockers> lockersList = lockerTypeService.getLockerByLockerType(lockerType);
            return returnBean.success("获取成功",lockersList);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return returnBean.error("此柜机类型不存在");
        }

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

        List<Lockers> lockersList = lockerTypeService.getLockerByLockerType(lockerType);
        if(!lockersList.isEmpty())
            return returnBean.error("修改失败,仍有此类型柜机在使用中");


        if (lockerTypeService.updateById(lockerType)){
            return returnBean.success("修改成功");
        }else return returnBean.error("修改失败");
    }

    @ApiOperation(value = "根据id删除柜机类型")
    @DeleteMapping("/delete/{lockerTypeId}")
    public returnBean delete(@PathVariable Integer lockerTypeId){
        LockerType lockerType=lockerTypeService.getById(lockerTypeId);
        List<Lockers> lockersList = lockerTypeService.getLockerByLockerType(lockerType);
        if(!lockersList.isEmpty())
            return returnBean.error("删除失败,仍有此类型柜机在使用中");
        if (lockerTypeService.removeById(lockerTypeId)){
            return returnBean.success("删除成功");
        }else return returnBean.error("删除失败");
    }



}
