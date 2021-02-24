package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Roles;

import java.util.List;


public interface RolesMapper extends BaseMapper<Roles> {

    List<Roles> getRoles(Integer userId);

}
