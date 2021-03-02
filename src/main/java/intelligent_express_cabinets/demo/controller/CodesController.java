package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.service.ICodesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/codes")
public class CodesController {

    @Autowired
    ICodesService codesService;

    @ApiOperation(value = "获取所有码")
    @GetMapping("")
    public returnBean getAll(){
        return returnBean.success("获取成功",codesService.list());
    }



}
