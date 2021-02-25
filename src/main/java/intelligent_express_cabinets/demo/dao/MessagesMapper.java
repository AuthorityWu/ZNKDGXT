package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Messages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessagesMapper extends BaseMapper<Messages> {

    List<Messages> getMessages(Integer userId);

}
