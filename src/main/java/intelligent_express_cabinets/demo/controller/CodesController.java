package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.service.impl.CodesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/codes")
public class CodesController {

    @Autowired
    CodesServiceImpl codesService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Codes> codesList=codesService.list();
        return ResultResponse.success(codesList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String id){
        Codes data=codesService.getById(id);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Codes data){
        if (codesService.getById(data.getCodeId())!=null){
            return ResultResponse.fail("资源已存在");
        }
        if (codesService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(Codes data){
        if (codesService.getById(data.getCodeId())!=null){
            if (codesService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String id){
        if (codesService.removeById(id)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
