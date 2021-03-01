package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.LockerType;
import intelligent_express_cabinets.demo.entity.Roles;
import intelligent_express_cabinets.demo.service.IRolesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/roles")
public class RolesController {
@Autowired
    IRolesService rolesService;

    @ApiOperation(value = "新建角色")
    @PostMapping("/add")
    public returnBean addRoles(@RequestBody Roles roles){
        if (rolesService.save(roles)){
            return returnBean.success("新建角色成功");
        }else return returnBean.error("新建角色成功");
    }
}
