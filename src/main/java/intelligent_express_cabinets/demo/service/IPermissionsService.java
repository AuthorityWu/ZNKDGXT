package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Permissions;

import java.util.List;


public interface IPermissionsService extends IService<Permissions> {

    List<Permissions> getPermissionByUserId();

    /**
     * 根据角色查询菜单列表
     * @return
     */
    List<Permissions> getPermissionsWithRole();

}
