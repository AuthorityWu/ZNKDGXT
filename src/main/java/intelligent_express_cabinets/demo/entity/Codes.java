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
@TableName("codes")
@ApiModel(value="Codes对象", description="码")
public class Codes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "code_id")
    private Integer codeId;

    @ApiModelProperty(value = "订单ID 外键 int")
    @TableField("order_id")
    private Integer orderId;

    @ApiModelProperty(value = "柜子id（柜机中的柜子编号）")
    @TableField("box_id")
    private Integer boxId;

    @ApiModelProperty(value = "状态* int (1.空闲可用  2.已绑定订单 3已绑定柜子 0不可用")
    @TableField("code_status")
    private Integer codeStatus;

    @ApiModelProperty(value = "柜机ID")
    @TableField("locker_id")
    private Integer lockerId;


}
