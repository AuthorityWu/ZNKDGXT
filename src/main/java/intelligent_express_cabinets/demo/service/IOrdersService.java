package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Orders;

import java.util.List;


public interface IOrdersService extends IService<Orders> {

    Orders getOrderByCode(Integer code);

    List<Orders> getOrdersByUserId(Integer userId);

    Orders getOrderByUserId(Integer orderId);
}
