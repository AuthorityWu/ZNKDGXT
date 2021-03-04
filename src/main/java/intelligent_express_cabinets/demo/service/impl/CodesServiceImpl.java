package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.CodesMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.service.IBoxesService;
import intelligent_express_cabinets.demo.service.ICodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CodesServiceImpl extends ServiceImpl<CodesMapper, Codes> implements ICodesService {

    @Autowired
    private CodesMapper codesMapper;
    @Autowired
    IBoxesService boxesService;

    @Override
    public Codes getByMaxId() {
        //return codesMapper.getByMaxId();
        return getNotUsed();
    }

    @Override
    public Codes getCodeByOrderId(Integer orderId) {
        //return codesMapper.selectOne(new QueryWrapper<Codes>().eq("order_id",orderId));
        return this.getOne(new QueryWrapper<Codes>().eq("order_id",orderId),false);
    }

    @Override
    public Codes getByBox(Boxes boxes) {
        QueryWrapper<Codes> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_id",boxes.getLockerId())
                .eq("box_id",boxes.getLockerBoxId());
        return this.getOne(queryWrapper,false);
    }

    public Codes getNotUsed(){
        QueryWrapper<Codes> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("code_status",1).last("LIMIT 1");
        Codes data=this.getOne(queryWrapper);
        if (data!=null)return data;
        else {
            data=new Codes();
            data.setBoxId(0);
            data.setLockerId(0);
            data.setOrderId(0);
            data.setCodeStatus(1);
            this.save(data);
        }
        return data;
    }
}
