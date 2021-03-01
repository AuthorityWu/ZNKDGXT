package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Permissions;

import java.util.List;


public interface IPermissionsService extends IService<Permissions> {

    List<Permissions> getPermissionByUserId(Integer userId);

    /**
     * 根据角色查询菜单列表
     * @return 权限集合
     */
    List<Permissions> getPermissionsWithRole();

}
