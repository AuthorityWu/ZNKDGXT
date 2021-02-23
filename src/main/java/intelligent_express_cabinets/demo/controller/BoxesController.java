package intelligent_express_cabinets.demo.controller;


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
    private BoxesServiceImpl boxesService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Boxes> boxesList=boxesService.list();
        return ResultResponse.success(boxesList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String boxId){
        Boxes data=boxesService.getById(boxId);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Boxes data){
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
    public Result deleteById(@RequestParam String boxId){
        if (boxesService.removeById(boxId)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }

    @RequestMapping("/useBox")
    public Result useBox(@RequestParam Integer LockerId,@RequestParam Integer lockerBoxId,@RequestParam Integer codeId){
        try {
            Boxes boxes=boxesService.findByLocker(LockerId,lockerBoxId);
            boxesService.bindCode(boxes,codeId);
            boxesService.useBox(boxes);
        }catch (Exception e){
            return ResultResponse.fail(e.getLocalizedMessage());
        }
        return ResultResponse.success();
    }
    @RequestMapping("/unUseBox")
    public Result unUseBox(@RequestParam Integer LockerId,@RequestParam Integer lockerBoxId){
        try {
            Boxes boxes=boxesService.findByLocker(LockerId,lockerBoxId);
            boxesService.unBindCode(boxes);
            boxesService.unUseBox(boxes);
        }catch (Exception e){
            return ResultResponse.fail(e.getLocalizedMessage());
        }
        return ResultResponse.success();
    }

    @RequestMapping("/reportBroken")
    public Result reportBroken(@RequestParam Integer LockerId,@RequestParam Integer lockerBoxId){
        try {
            Boxes boxes=boxesService.findByLocker(LockerId,lockerBoxId);
            boxesService.reportBroken(boxes);
        }catch (Exception e){
            return ResultResponse.fail(e.getLocalizedMessage());
        }
        return ResultResponse.success();
    }



}
