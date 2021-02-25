package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.service.IBoxesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/boxes")
public class BoxesController {
    @Autowired
    IBoxesService boxesService;



}
