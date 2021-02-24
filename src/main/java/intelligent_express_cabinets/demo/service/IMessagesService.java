package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Messages;

import java.util.List;


public interface IMessagesService extends IService<Messages> {


    List<Messages> getMessages(Integer userId);

}
