package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.UsersMapper;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.IUsersService;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
