package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.LockerType;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.service.impl.LockerTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/lockerType")
public class LockerTypeController {

    @Autowired
    LockerTypeServiceImpl lockerTypeService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<LockerType> LockerTypeList=lockerTypeService.list();
        return ResultResponse.success(LockerTypeList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String lockerTypeId){
        LockerType data=lockerTypeService.getById(lockerTypeId);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(LockerType data){

        if (lockerTypeService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(LockerType data){
        if (lockerTypeService.getById(data.getLockerTypeId())!=null){
            if (lockerTypeService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String lockerTypeId){
        if (lockerTypeService.removeById(lockerTypeId)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }


}
