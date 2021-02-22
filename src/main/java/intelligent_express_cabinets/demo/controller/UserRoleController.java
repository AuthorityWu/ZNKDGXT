package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.entity.UserRole;
import intelligent_express_cabinets.demo.service.impl.UserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user-role")
public class UserRoleController {
    @Autowired
    UserRoleServiceImpl userRoleService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<UserRole> UserRoleList= userRoleService.list();
        return ResultResponse.success(UserRoleList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String id){
        UserRole data= userRoleService.getById(id);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(UserRole data){
        if (userRoleService.getById(data.getUserRoleId())!=null){
            return ResultResponse.fail("资源已存在");
        }
        if (userRoleService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(UserRole data){
        if (userRoleService.getById(data.getUserRoleId())!=null){
            if (userRoleService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String id){
        if (userRoleService.removeById(id)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
