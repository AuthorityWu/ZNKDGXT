package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Orders;

import java.util.List;


public interface OrdersMapper extends BaseMapper<Orders> {

    List<Orders> getOrdersByUserId(Integer userId);
}
