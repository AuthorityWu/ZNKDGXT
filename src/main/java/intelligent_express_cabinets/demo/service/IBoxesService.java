package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Codes;


public interface IBoxesService extends IService<Boxes> {

    Boxes getBoxes(Integer boxId);

    Boxes getByLockerIdLockerBoxId(Integer lockerId,Integer boxId);

    void bindCode(Boxes boxes, Codes codes);

    void noBindCode(Codes codes);
}
