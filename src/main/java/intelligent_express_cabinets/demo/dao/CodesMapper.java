package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Codes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodesMapper extends BaseMapper<Codes> {

    Codes getByMaxId();
}
