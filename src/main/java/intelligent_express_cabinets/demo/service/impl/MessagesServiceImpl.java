package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.MessagesMapper;
import intelligent_express_cabinets.demo.entity.Messages;
import intelligent_express_cabinets.demo.service.IMessagesService;
import org.springframework.stereotype.Service;


@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements IMessagesService {

}
