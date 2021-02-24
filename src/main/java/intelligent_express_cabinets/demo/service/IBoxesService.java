package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Boxes;


public interface IBoxesService extends IService<Boxes> {

    Boxes getBoxes(Integer boxId);

    Boxes getByLockerIdBoxId(Integer lockerId,Integer boxId);
}
