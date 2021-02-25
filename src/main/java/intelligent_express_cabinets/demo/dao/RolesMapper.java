package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Roles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolesMapper extends BaseMapper<Roles> {

    List<Roles> getRoles(Integer userId);

}
