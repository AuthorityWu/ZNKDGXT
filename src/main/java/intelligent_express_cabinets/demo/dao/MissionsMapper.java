package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Missions;

import java.util.List;


public interface MissionsMapper extends BaseMapper<Missions> {

    List<Missions> getMissionsByNotReceive();

    List<Missions> getMissionsByReceive();

    List<Missions> getMissionsByFinishReceive();
}
