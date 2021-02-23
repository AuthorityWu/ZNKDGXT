package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.entity.Roles;
import intelligent_express_cabinets.demo.service.impl.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    RolesServiceImpl rolesService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Roles> RolesList=rolesService.list();
        return ResultResponse.success(RolesList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String roleId){
        Roles data=rolesService.getById(roleId);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Roles data){

        if (rolesService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(Roles data){
        if (rolesService.getById(data.getRoleId())!=null){
            if (rolesService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String roleId){
        if (rolesService.removeById(roleId)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
