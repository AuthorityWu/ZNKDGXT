package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.BoxesMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.service.IBoxesService;
import org.springframework.stereotype.Service;


@Service
public class BoxesServiceImpl extends ServiceImpl<BoxesMapper, Boxes> implements IBoxesService {

}
