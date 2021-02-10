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
@TableName("boxes")
@ApiModel(value="Boxes对象", description="")
public class Boxes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "box_id", type = IdType.AUTO)
    private Integer boxId;

    @ApiModelProperty(value = "柜机ID")
    @TableField("locker_id")
    private Integer lockerId;

    @ApiModelProperty(value = "柜机中柜子的编号")
    @TableField("locker_box_id")
    private Integer lockerBoxId;

    @ApiModelProperty(value = "柜子类型 1大，2中，3小")
    @TableField("box_size")
    private Integer boxSize;

    @ApiModelProperty(value = "快递柜柜子的状态   0.不可用 1.未使用（正常）2.使用中（正常）")
    @TableField("box_status")
    private Integer boxStatus;


}
