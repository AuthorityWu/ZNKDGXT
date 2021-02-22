package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Missions;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.service.impl.MissionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/missions")
public class MissionsController {

    @Autowired
    MissionsServiceImpl missionsService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Missions> MissionsList=missionsService.list();
        return ResultResponse.success(MissionsList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String id){
        Missions data=missionsService.getById(id);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Missions data){
        if (missionsService.getById(data.getMissionId())!=null){
            return ResultResponse.fail("资源已存在");
        }
        if (missionsService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(Missions data){
        if (missionsService.getById(data.getMissionId())!=null){
            if (missionsService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String id){
        if (missionsService.removeById(id)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
