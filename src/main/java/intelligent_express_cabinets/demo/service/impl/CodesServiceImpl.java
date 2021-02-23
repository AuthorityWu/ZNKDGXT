package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.CodesMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.service.ICodesService;
import net.bytebuddy.implementation.bind.annotation.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CodesServiceImpl extends ServiceImpl<CodesMapper, Codes> implements ICodesService {


    public Codes getNotUsed(){
        QueryWrapper<Codes> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("code_status",1).last("LIMIT 1");
        Codes data=this.getOne(queryWrapper);
        if (data!=null)return data;
        else {
            data=new Codes();
            data.setBoxId(0);
            data.setOrderId(0);
            data.setCodeStatus(1);
            this.save(data);
        }
        return data;
    }


}
