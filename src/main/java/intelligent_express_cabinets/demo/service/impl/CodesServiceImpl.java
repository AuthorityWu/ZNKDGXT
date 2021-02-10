package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.CodesMapper;
import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.service.ICodesService;
import org.springframework.stereotype.Service;


@Service
public class CodesServiceImpl extends ServiceImpl<CodesMapper, Codes> implements ICodesService {

}
