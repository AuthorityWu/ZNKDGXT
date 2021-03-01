package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.PermissionsMapper;
import intelligent_express_cabinets.demo.entity.Permissions;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<Permissions> getPermissionByUserId(Integer userId) {
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        //从redis获取菜单数据
        List<Permissions> permissions = (List<Permissions>) valueOperations.get("menu_"+ userId);
        //如果为空，去数据库获取数据
        if (CollectionUtils.isEmpty(permissions)){
            permissions = permissionsMapper.getPermissionByUserId(userId);
            //将数据设置到Redis中
            valueOperations.set("menu_"+ userId,permissions);
        }
        return permissions;
    }

    @Override
    public List<Permissions> getPermissionsWithRole() {
        return permissionsMapper.getPermissionsWithRole();
    }
}
