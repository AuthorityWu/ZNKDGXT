package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.OrdersMapper;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public Orders getOrderByCode(Integer code) {
        return ordersMapper.selectOne(new QueryWrapper<Orders>().eq("order_code",code));
    }

    @Override
    public List<Orders> getOrdersByUserId(Integer userId) {
        return ordersMapper.getOrdersByUserId(userId);
    }

    @Override
    public Orders getOrderByUserId(Integer orderId) {
        return ordersMapper.selectOne(new QueryWrapper<Orders>().eq("order_id",orderId));
    }

}
