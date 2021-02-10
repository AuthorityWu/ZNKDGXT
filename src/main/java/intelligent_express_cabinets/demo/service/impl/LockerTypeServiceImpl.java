package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.LockerTypeMapper;
import intelligent_express_cabinets.demo.entity.LockerType;
import intelligent_express_cabinets.demo.service.ILockerTypeService;
import org.springframework.stereotype.Service;


@Service
public class LockerTypeServiceImpl extends ServiceImpl<LockerTypeMapper, LockerType> implements ILockerTypeService {

}
