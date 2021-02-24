package intelligent_express_cabinets.demo.controller;


import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.entity.Messages;
import intelligent_express_cabinets.demo.entity.Orders;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.IMessagesService;
import intelligent_express_cabinets.demo.service.IOrdersService;
import intelligent_express_cabinets.demo.service.IUsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/messages/basic")
public class MessagesController {

    @Resource
    private IMessagesService messagesService;

    @Resource
    private IUsersService usersService;

    @Resource
    private IOrdersService ordersService;

    @ApiOperation(value = "获取所有会员消息信息")
    @GetMapping("/member/messages")
    public returnBean getMemberMessages(Principal principal){
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        List<Messages> messagesList = messagesService.getMessages(users.getUserId());
        return returnBean.success("获取所有会员消息信息成功!",messagesList);
    }

    @ApiOperation(value = "根据会员id删除消息信息")
    @DeleteMapping("/delete/member/{messageId}")
    public returnBean deleteMemberOrder(@PathVariable Integer messageId){
        Messages messages = messagesService.getById(messageId);
        //设置消息状态为2:消息不可见(会员、专柜员删除，管理员可见)
        messages.setMessageStatus(2);
        messagesService.updateById(messages);
        return returnBean.success("删除消息成功!");
    }

    @ApiOperation(value = "发送取件失败消息")
    @PostMapping("/send/member/{orderId}")
    public returnBean sendMemberOrders(@PathVariable Integer orderId, Principal principal){
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        Messages messages = new Messages();
        messages.setMessageStatus(1);
        messages.setMessageContent("我已经输入了存储码,但是取快件失败!");
        messages.setMessageType(3);
        messages.setSendId(users.getUserId());
        messagesService.save(messages);
        Orders order = ordersService.getById(orderId);
        //设置订单状态为6：专柜员处理中
        order.setOrderStatus(6);
        ordersService.updateById(order);
        return returnBean.success("发送取件失败消息成功!");
    }

    @ApiOperation(value = "获取所有专柜员消息信息")
    @GetMapping("/counter/messages")
    public returnBean getCounterMessages(Principal principal){
        String username = principal.getName();
        Users users = usersService.getUserByUsername(username);
        List<Messages> messagesList = messagesService.getMessages(users.getUserId());
        return returnBean.success("获取所有专柜员消息信息成功!",messagesList);
    }

    @ApiOperation(value = "根据专柜员id删除消息信息")
    @DeleteMapping("/delete/counter/{messageId}")
    public returnBean deleteCounterOrder(@PathVariable Integer messageId){
        Messages messages = messagesService.getById(messageId);
        //设置消息状态为2:消息不可见(会员、专柜员删除，管理员可见)
        messages.setMessageStatus(2);
        messagesService.updateById(messages);
        return returnBean.success("删除专柜员消息信息成功!");
    }
}
