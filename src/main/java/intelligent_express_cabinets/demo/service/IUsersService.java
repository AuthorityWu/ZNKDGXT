package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.Roles;
import intelligent_express_cabinets.demo.entity.Users;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface IUsersService extends IService<Users> {

    returnBean login(String username, String password, HttpServletRequest request);

    Users getUserByUsername(String username);

    List<Roles> getRoles(Integer userId);
}
