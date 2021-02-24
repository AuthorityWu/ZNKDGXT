package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.MissionsMapper;
import intelligent_express_cabinets.demo.entity.Missions;
import intelligent_express_cabinets.demo.service.IMissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MissionsServiceImpl extends ServiceImpl<MissionsMapper, Missions> implements IMissionsService {

    @Autowired
    private MissionsMapper missionsMapper;

    @Override
    public List<Missions> getMissionsByNotReceive() {
        return missionsMapper.getMissionsByNotReceive();
    }

    @Override
    public List<Missions> getMissionsByReceive() {
        return missionsMapper.getMissionsByReceive();
    }

    @Override
    public List<Missions> getMissionsByFinishReceive() {
        return missionsMapper.getMissionsByFinishReceive();
    }
}
