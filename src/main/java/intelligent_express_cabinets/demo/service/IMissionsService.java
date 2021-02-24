package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Missions;

import java.util.List;


public interface IMissionsService extends IService<Missions> {

    List<Missions> getMissionsByNotReceive();

    List<Missions> getMissionsByReceive();

    List<Missions> getMissionsByFinishReceive();
}
