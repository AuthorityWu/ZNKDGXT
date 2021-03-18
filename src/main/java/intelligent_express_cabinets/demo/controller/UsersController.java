package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.UserRole;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.IRolesService;
import intelligent_express_cabinets.demo.service.IUserRoleService;
import intelligent_express_cabinets.demo.service.IUsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping
public class UsersController {

    @Resource
    private IUsersService usersService;

    @Resource
    private IUserRoleService userRoleService;

    @Autowired
    IRolesService rolesService;

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

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/user")
    public returnBean getAllUser(){
        List<Users> usersList = usersService.list();
        for (Users users:usersList
             ) {
            users.setRoles(usersService.getRoles(users.getUserId()));

        }
        return returnBean.success("获取成功",usersList);

    }

    @ApiOperation(value = "获取所有管理员")
    @GetMapping("/admin")
    public returnBean getAllAdmin(){
        List<Users> usersList = usersService.list();
        List<Users> adminList=new ArrayList<>();
        for (Users users:usersList
        ) {
            users.setRoles(usersService.getRoles(users.getUserId()));
            //是管理员
            if (!users.getRoles().isEmpty() && users.getRoles().get(0).getRoleId()==1)
                adminList.add(users);
        }

        return returnBean.success("获取管理员成功",adminList);

    }


    @ApiOperation(value = "获取所有用户(客户)")
    @GetMapping("/customer")
    public returnBean getAllCustomer(){
        List<Users> usersList = usersService.list();
        List<Users> customerList=new ArrayList<>();
        for (Users users:usersList
        ) {
            users.setRoles(usersService.getRoles(users.getUserId()));
            //是管理员
            if (!users.getRoles().isEmpty() && users.getRoles().get(0).getRoleId()==2)
                customerList.add(users);
        }

        return returnBean.success("获取管理员成功",customerList);

    }


    @ApiOperation(value = "获取所有专柜员")
    @GetMapping("/staff")
    public returnBean getAllStaff(){
        List<Users> usersList = usersService.list();
        List<Users> staffList=new ArrayList<>();
        for (Users users:usersList
        ) {
            users.setRoles(usersService.getRoles(users.getUserId()));
            //是管理员
            if (!users.getRoles().isEmpty() && users.getRoles().get(0).getRoleId()==3)
                staffList.add(users);
        }

        return returnBean.success("获取专柜员成功",staffList);

    }



    @ApiOperation(value = "通过id删除用户")
    @DeleteMapping("/user/delete/{userId}")
    public returnBean deleteUserByUserId(@PathVariable Integer userId){
        if(usersService.removeById(userId)) {
            return returnBean.success("删除成功");
        }else return returnBean.error("失败");
    }

    @ApiOperation(value = "添加专柜员")
    @PostMapping("/staff/add")
    public returnBean staffRegister(@RequestBody Users users){
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
                userRole.setRoleId(3);
                boolean bool= userRoleService.save(userRole);
                if (bool){
                    return returnBean.success("新专柜员用户注册成功!");
                }
                else {
                    return returnBean.error("新专柜员用户注册失败!");
                }
            }
        }
        return returnBean.error("此新用户的账号已经存在!");
    }

    @ApiOperation(value = "添加管理员")
    @PostMapping("/admin/add")
    public returnBean adminRegister(@RequestBody Users users){
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
                //设置管理员
                userRole.setRoleId(1);
                boolean bool= userRoleService.save(userRole);
                if (bool){
                    return returnBean.success("新管理员用户注册成功!");
                }
                else {
                    return returnBean.error("新管理员用户注册失败!");
                }
            }
        }
        return returnBean.error("此新用户的账号已经存在!");
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/user/info")
    public returnBean getCustomerInfo(Principal principal){
        if(null==principal){
            return returnBean.error("没有当前登录人员的信息");
        }
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        users.setRoles(usersService.getRoles(users.getUserId()));
        users.setPassword(null);
        return returnBean.success("获取成功",users);
    }

    @ApiOperation(value = "更新用户的个人信息")
    @PostMapping ("/user/update")
    public returnBean updateMembers(@RequestBody Users users){
        users.setUsername(null);
        users.setPassword(null);
        if (usersService.updateById(users)){
            return returnBean.success("除了用户名和密码以外的信息更新成功!");
        }
        return returnBean.error("更新失败!");
    }

    @ApiOperation(value ="当前用户更改密码")
    @PostMapping("/resetPassword")
    public returnBean resetPassword(@RequestParam String password,Principal principal){
        PasswordEncoder pe = new BCryptPasswordEncoder();
        String encodePassword = pe.encode(password);
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        users.setPassword(encodePassword);
         if(usersService.updateById(users)){
             return returnBean.success("成功");
         }
        return returnBean.error("失败");
    }

}
