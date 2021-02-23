package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.entity.RolePermission;
import intelligent_express_cabinets.demo.service.impl.RolePermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/role-permission")
public class RolePermissionController {
    @Autowired
    RolePermissionServiceImpl rolePermissionService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<RolePermission> RolePermissionList= rolePermissionService.list();
        return ResultResponse.success(RolePermissionList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String rolePermissionId){
        RolePermission data= rolePermissionService.getById(rolePermissionId);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(RolePermission data){

        if (rolePermissionService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(RolePermission data){
        if (rolePermissionService.getById(data.getRolePermissionId())!=null){
            if (rolePermissionService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String rolePermissionId){
        if (rolePermissionService.removeById(rolePermissionId)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
