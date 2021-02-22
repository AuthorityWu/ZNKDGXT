package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.service.impl.LockersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/lockers")
public class LockersController {

    @Autowired
    LockersServiceImpl lockersService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Lockers> lockersList=lockersService.list();
        return ResultResponse.success(lockersList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String id){
        Lockers data=lockersService.getById(id);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Lockers data){
        if (lockersService.getById(data.getLockerId())!=null){
            return ResultResponse.fail("资源已存在");
        }
        if (lockersService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(Lockers data){
        if (lockersService.getById(data.getLockerId())!=null){
            if (lockersService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String id){
        if (lockersService.removeById(id)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
