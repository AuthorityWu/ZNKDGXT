package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Lockers;

import java.util.List;


public interface LockersMapper extends BaseMapper<Lockers> {

    List<Lockers> getLockerBoxes(int boxSize);
}
