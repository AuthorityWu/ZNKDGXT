package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
//import intelligent_express_cabinets.demo.service.impl.ordersServiceImpl;
import intelligent_express_cabinets.demo.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersServiceImpl ordersService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Orders> OrdersList=ordersService.list();
        return ResultResponse.success(OrdersList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String orderId){
        Orders data=ordersService.getById(orderId);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Orders data){

        if (ordersService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }
    @RequestMapping("/CreateNewOrder")
    public Result CreateNewOrder(Orders data){

        ordersService.bindNotUsedCode(data);
        if (ordersService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }


    @RequestMapping("/update")
    public Result update(Orders data){
        if (ordersService.getById(data.getOrderId())!=null){
            if (ordersService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String orderId){
        if (ordersService.removeById(orderId)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
