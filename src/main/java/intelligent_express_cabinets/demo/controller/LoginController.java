package intelligent_express_cabinets.demo.controller;

import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.UserLoginParam;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Resource
    private IUsersService usersService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public returnBean login(@RequestBody UserLoginParam usersLoginParam, HttpServletRequest request){
        return usersService.login(usersLoginParam.getUsername(),usersLoginParam.getPassword(),request);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public returnBean logout(){
        return returnBean.success("注销成功！");
    }

}
