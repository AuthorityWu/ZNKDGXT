package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.entity.Messages;
import intelligent_express_cabinets.demo.entity.Messages;
import intelligent_express_cabinets.demo.entity.Result;
import intelligent_express_cabinets.demo.entity.ResultResponse;
//import intelligent_express_cabinets.demo.service.impl.messagesServiceImpl;
import intelligent_express_cabinets.demo.service.impl.MessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    MessagesServiceImpl messagesService;

    @RequestMapping("/findAll")
    public Result findAll(){

        List<Messages> MessagesList=messagesService.list();
        return ResultResponse.success(MessagesList);
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam String messageId){
        Messages data=messagesService.getById(messageId);
        if (data!=null){
            return ResultResponse.success(data);
        }else return ResultResponse.notFound();
    }

    @RequestMapping("/insert")
    public Result insert(Messages data){

        if (messagesService.save(data)) {
            return ResultResponse.success();
        }
        else return ResultResponse.fail("添加不成功");
    }

    @RequestMapping("/update")
    public Result update(Messages data){
        if (messagesService.getById(data.getMessageId())!=null){
            if (messagesService.saveOrUpdate(data)) {
                return ResultResponse.success();
            } else return ResultResponse.fail("更新不成功");
        }else return ResultResponse.notFound();
    }
    @RequestMapping("/deleteById")
    public Result deleteById(@RequestParam String messageId){
        if (messagesService.removeById(messageId)){
            return ResultResponse.success();
        }else return ResultResponse.fail("删除失败");
    }
}
