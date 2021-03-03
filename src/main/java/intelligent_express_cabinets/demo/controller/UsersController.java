package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.UserRole;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.IUserRoleService;
import intelligent_express_cabinets.demo.service.IUsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;


@RestController
@RequestMapping
public class UsersController {

    @Resource
    private IUsersService usersService;

    @Resource
    private IUserRoleService userRoleService;

    @ApiOperation(value = "新用户注册")
    @PostMapping("/register")
    public returnBean userRegister(@RequestBody Users users){
        Users user = usersService.getUserByUsername(users.getUsername());
        if (user==null){
            //先把用户输入的明文密码进行加密
            PasswordEncoder pe = new BCryptPasswordEncoder();
            String encode = pe.encode(users.getPassword());
            users.setPassword(encode);
            users.setUserStatus(0);
            boolean booleans = usersService.save(users);
            if (booleans){
                Users user1 = usersService.getUserByUsername(users.getUsername());
                Integer userId = user1.getUserId();
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(2);
                boolean bool= userRoleService.save(userRole);
                if (bool){
                    return returnBean.success("新用户注册成功!");
                }
                else {
                    return returnBean.error("新用户注册失败!");
                }
            }
        }
        return returnBean.error("此新用户的账号已经存在!");
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/user/info")
    public returnBean getCustomerInfo(Principal principal){
        if(null==principal){
            return null;
        }
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        users.setRoles(usersService.getRoles(users.getUserId()));
        users.setPassword(null);
        return returnBean.success("获取成功",users);
    }

    @ApiOperation(value = "更新会员的个人信息")
    @PostMapping ("/user/update")
    public returnBean updateMembers(@RequestBody Users users){
        if (usersService.updateById(users)){
            return returnBean.success("更新成功!");
        }
        return returnBean.error("更新失败!");
    }


}
