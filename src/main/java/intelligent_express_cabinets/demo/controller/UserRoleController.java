package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.Roles;
import intelligent_express_cabinets.demo.entity.UserRole;
import intelligent_express_cabinets.demo.service.IUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user-role")
public class UserRoleController {

    @Autowired
    IUserRoleService userRoleService;
    @ApiOperation(value = "添加用户-角色关联")
    @PostMapping("/add")
    public returnBean addconnext(@RequestBody UserRole userRole){
        if (userRoleService.save(userRole)){
            return returnBean.success("添加用户-角色关联成功");
        }else return returnBean.error("添加失败");
    }
}
