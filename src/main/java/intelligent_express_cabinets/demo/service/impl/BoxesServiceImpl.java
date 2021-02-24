package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.BoxesMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.service.IBoxesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoxesServiceImpl extends ServiceImpl<BoxesMapper, Boxes> implements IBoxesService {

    @Autowired
    private BoxesMapper boxesMapper;

    @Override
    public Boxes getBoxes(Integer boxId) {
        return boxesMapper.selectOne(new QueryWrapper<Boxes>().eq("box_id",boxId));
    }

    @Override
    public Boxes getByLockerIdBoxId(Integer lockerId,Integer boxId) {
        return boxesMapper.getByLockerIdBoxId(lockerId,boxId);
    }
}
