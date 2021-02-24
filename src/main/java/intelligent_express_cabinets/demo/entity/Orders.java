package intelligent_express_cabinets.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orders")
@ApiModel(value="Orders对象", description="")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "会员ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "寄件人地址")
    @TableField("send_address")
    private String sendAddress;

    @ApiModelProperty(value = "寄件人姓名")
    @TableField("send_name")
    private String sendName;

    @ApiModelProperty(value = "寄件人手机")
    @TableField("send_tel")
    private String sendTel;

    @ApiModelProperty(value = "收件人地址")
    @TableField("receive_address")
    private String receiveAddress;

    @ApiModelProperty(value = "收件人姓名")
    @TableField("receive_name")
    private String receiveName;

    @ApiModelProperty(value = "收件人手机")
    @TableField("receive_tel")
    private String receiveTel;

    @ApiModelProperty(value = "存取码")
    @TableField("order_code")
    private Integer orderCode;

    @ApiModelProperty(value = "类型 1.会员寄件 2.快递员派件")
    @TableField("order_type")
    private Integer orderType;

    @ApiModelProperty(value = "快递公司")
    @TableField("order_company")
    private String orderCompany;

    @ApiModelProperty(value = "状态* int  1.待存件 2.待取件 3.完成 4.已取消 5.订单不可见（会员删除，管理员可见）6.等待专员处理 0.注销")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField("order_start_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDateTime orderStartTime;

    @ApiModelProperty(value = "完成时间")
    @TableField("order_end_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDateTime orderEndTime;

    @ApiModelProperty(value = "运单编号 条件：订单类型为2 string")
    @TableField("express_number")
    private String expressNumber;

    @ApiModelProperty(value = "派件员手机 条件：订单类型为2 string")
    @TableField("expressman_tel")
    private String expressmanTel;

    @ApiModelProperty(value = "柜子大小* int  1.小 2.中 3.大")
    @TableField("box_size")
    private Integer boxSize;


}
