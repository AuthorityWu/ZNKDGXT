package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Permissions;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.service.impl.PermissionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/permissions")
public class PermissionsController {
    @Autowired
    PermissionsServiceImpl permissionsService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Permissions> PermissionsList=permissionsService.list();
        return ResultResponse.success(PermissionsList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String permissionId){
        Permissions data=permissionsService.getById(permissionId);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Permissions data){

        if (permissionsService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(Permissions data){
        if (permissionsService.getById(data.getPermissionId())!=null){
            if (permissionsService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String permissionId){
        if (permissionsService.removeById(permissionId)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
