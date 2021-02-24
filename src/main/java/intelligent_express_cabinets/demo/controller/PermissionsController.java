package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Permissions;
import intelligent_express_cabinets.demo.service.IPermissionsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/system/permissions")
public class PermissionsController {

    @Autowired
    private IPermissionsService permissionsService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Permissions> getPermissionByUserId(){
        return permissionsService.getPermissionByUserId();
    }
}
