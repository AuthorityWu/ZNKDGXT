package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Permissions;

import java.util.List;


public interface PermissionsMapper extends BaseMapper<Permissions> {

    List<Permissions> getPermissionByUserId(Integer userId);

    List<Permissions> getPermissionsWithRole();
}
