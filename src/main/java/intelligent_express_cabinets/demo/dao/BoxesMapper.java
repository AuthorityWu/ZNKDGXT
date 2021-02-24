package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Boxes;


public interface BoxesMapper extends BaseMapper<Boxes> {

    Boxes getByLockerIdBoxId(Integer lockerId, Integer boxId);
}
