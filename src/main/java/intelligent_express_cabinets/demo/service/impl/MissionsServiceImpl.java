package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.MissionsMapper;
import intelligent_express_cabinets.demo.entity.Missions;
import intelligent_express_cabinets.demo.service.IMissionsService;
import org.springframework.stereotype.Service;


@Service
public class MissionsServiceImpl extends ServiceImpl<MissionsMapper, Missions> implements IMissionsService {

}
