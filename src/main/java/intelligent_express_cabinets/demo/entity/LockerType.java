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
@TableName("locker_type")
@ApiModel(value="LockerType对象", description="")
public class LockerType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "locker_type_id", type = IdType.AUTO)
    private Integer lockerTypeId;

    @ApiModelProperty(value = "柜机类型名称")
    @TableField("locker_type_name")
    private String lockerTypeName;

    @ApiModelProperty(value = "大柜子数量 int")
    @TableField("big_box_number")
    private Integer bigBoxNumber;

    @ApiModelProperty(value = "中柜子数量 int")
    @TableField("middle_box_number")
    private Integer middleBoxNumber;

    @ApiModelProperty(value = "小柜子数量 int")
    @TableField("small_box_number")
    private Integer smallBoxNumber;


}
