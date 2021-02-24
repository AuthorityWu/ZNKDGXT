package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.CodesMapper;
import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.service.ICodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CodesServiceImpl extends ServiceImpl<CodesMapper, Codes> implements ICodesService {

    @Autowired
    private CodesMapper codesMapper;

    @Override
    public Codes getByMaxId() {
        return codesMapper.getByMaxId();
    }

    @Override
    public Codes getCodeByOrderId(Integer orderId) {
        return codesMapper.selectOne(new QueryWrapper<Codes>().eq("order_id",orderId));
    }
}
