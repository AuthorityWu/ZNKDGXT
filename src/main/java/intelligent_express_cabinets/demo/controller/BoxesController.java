package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.dao.BoxesMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
import intelligent_express_cabinets.demo.service.impl.BoxesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/boxes")
public class BoxesController {
    @Autowired
    private BoxesMapper boxesMapper;
    @Autowired
    private BoxesServiceImpl boxesService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Boxes> boxesList=boxesService.list();
        return ResultResponse.success(boxesList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String id){
        Boxes data=boxesService.getById(id);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Boxes data){
        if (boxesService.getById(data.getBoxId())!=null){
            return ResultResponse.fail("资源已存在");
        }
        if (boxesService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(Boxes data){
        if (boxesService.getById(data.getBoxId())!=null){
            if (boxesService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String id){
        if (boxesService.removeById(id)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }

}
