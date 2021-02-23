package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.impl.OrdersServiceImpl;
import intelligent_express_cabinets.demo.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersServiceImpl usersService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Users> OrdersList=usersService.list();
        return ResultResponse.success(OrdersList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String userId){
        Users data=usersService.getById(userId);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Users data){

        if (usersService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(Users data){
        if (usersService.getById(data.getUserId())!=null){
            if (usersService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String userId){
        if (usersService.removeById(userId)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
