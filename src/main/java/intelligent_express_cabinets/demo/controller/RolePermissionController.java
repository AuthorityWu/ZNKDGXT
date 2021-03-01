package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.Permissions;
import intelligent_express_cabinets.demo.entity.RolePermission;
import intelligent_express_cabinets.demo.service.IRolePermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role-permission")
public class RolePermissionController {
    @Autowired
    IRolePermissionService rolePermissionService;
    @ApiOperation(value = "添加权限角色关联")
    @PostMapping("add")
    public returnBean addPermissions(@RequestBody RolePermission rolePermission){
        if(rolePermissionService.save(rolePermission)){
            return returnBean.success("添加权限角色关联成功");
        }else return returnBean.error("添加权限角色关联失败");
    }
}
