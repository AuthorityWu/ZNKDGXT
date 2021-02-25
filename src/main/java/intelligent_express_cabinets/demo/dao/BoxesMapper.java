package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoxesMapper extends BaseMapper<Boxes> {

    Boxes getByLockerIdBoxId(Integer lockerId, Integer lockerBoxId);
}
