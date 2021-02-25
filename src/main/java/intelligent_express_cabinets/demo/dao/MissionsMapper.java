package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Missions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MissionsMapper extends BaseMapper<Missions> {

    List<Missions> getMissionsByNotReceive();

    List<Missions> getMissionsByReceive();

    List<Missions> getMissionsByFinishReceive();
}
