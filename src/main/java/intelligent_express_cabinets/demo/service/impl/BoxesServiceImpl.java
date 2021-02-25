package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.BoxesMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.service.IBoxesService;
import intelligent_express_cabinets.demo.service.ICodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoxesServiceImpl extends ServiceImpl<BoxesMapper, Boxes> implements IBoxesService {

    //@Autowired
    private BoxesMapper boxesMapper;

    @Autowired
    ICodesService codesService;

    @Override
    public Boxes getBoxes(Integer boxId) {
        return boxesMapper.selectOne(new QueryWrapper<Boxes>().eq("box_id",boxId));
    }

/*    @Override
    public Boxes getByLockerIdBoxId(Integer lockerId,Integer boxId) {
        return boxesMapper.getByLockerIdBoxId(lockerId,boxId);
    }*/

    @Override
    public Boxes getByLockerIdLockerBoxId(Integer lockerId,Integer boxId) {
        QueryWrapper<Boxes> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_id",lockerId)
                .eq("locker_box_id",boxId);
        return this.getOne(queryWrapper,false);

    }

    @Override
    public void bindCode(Boxes boxes, Codes codes) {

        codes.setBoxId(boxes.getBoxId());
    }

    @Override
    public void noBindCode(Codes codes) {
        codes.setBoxId(0);
    }

}
