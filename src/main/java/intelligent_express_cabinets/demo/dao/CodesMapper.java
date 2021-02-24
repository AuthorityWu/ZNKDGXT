package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Codes;


public interface CodesMapper extends BaseMapper<Codes> {

    Codes getByMaxId();
}
