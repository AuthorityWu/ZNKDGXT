package intelligent_express_cabinets.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("messages")
@ApiModel(value="Messages对象", description="")
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    @ApiModelProperty(value = "消息类型* int  1.寄件消息 2.取件消息 3.批量任务分配消息 4.取件失败反馈消息 5.抱怨投诉反馈消息 6.反馈的答复消息 7.取消订单消息")
    @TableField("message_type")
    private Integer messageType;

    @ApiModelProperty(value = "发送者ID 外键 int")
    @TableField("send_id")
    private Integer sendId;

    @ApiModelProperty(value = "接收者ID 外键 int")
    @TableField("receive_id")
    private Integer receiveId;

    @ApiModelProperty(value = "状态 int  1.正常 2.消息不可见(会员、专柜员删除，管理员可见) 0.注销")
    @TableField("message_status")
    private Integer messageStatus;

    @ApiModelProperty(value = "内容 string")
    @TableField("message_content")
    private String messageContent;


}
