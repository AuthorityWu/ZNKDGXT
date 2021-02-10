package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.UserRoleMapper;
import intelligent_express_cabinets.demo.entity.UserRole;
import intelligent_express_cabinets.demo.service.IUserRoleService;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
