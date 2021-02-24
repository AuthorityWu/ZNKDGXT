package intelligent_express_cabinets.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import intelligent_express_cabinets.demo.entity.Messages;

import java.util.List;


public interface MessagesMapper extends BaseMapper<Messages> {

    List<Messages> getMessages(Integer userId);

}
