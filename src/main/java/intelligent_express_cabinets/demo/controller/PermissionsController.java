package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.Permissions;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.IPermissionsService;
import intelligent_express_cabinets.demo.service.IUsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/system/permissions")
public class PermissionsController {

    @Autowired
    private IPermissionsService permissionsService;

    @Autowired
    IUsersService usersService;

    @ApiOperation(value = "添加权限类型")
    @PostMapping("add")
    public returnBean addPermissions(@RequestBody Permissions permissions){
        if(permissionsService.save(permissions)){
            return returnBean.success("添加权限类型成功");
        }else return returnBean.error("添加权限类型失败");
    }



    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public returnBean  getPermissionByUserId(Principal principal){
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        List<Permissions> permissionsList = permissionsService.getPermissionByUserId(users.getUserId());
        return returnBean.success("成功" ,permissionsList);
    }
}
