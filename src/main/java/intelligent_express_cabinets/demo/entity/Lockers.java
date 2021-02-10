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
@TableName("lockers")
@ApiModel(value="Lockers对象", description="")
public class Lockers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "locker_id", type = IdType.AUTO)
    private Integer lockerId;

    @ApiModelProperty(value = "柜机型号 外键 int")
    @TableField("locker_type_id")
    private Integer lockerTypeId;

    @ApiModelProperty(value = "经度")
    private Float longitude;

    @ApiModelProperty(value = "纬度")
    private Float latitude;

    @ApiModelProperty(value = "大柜子可用数量 int")
    @TableField("big_box_able_number")
    private Integer bigBoxAbleNumber;

    @ApiModelProperty(value = "中柜子可用数量 int")
    @TableField("middle_box_able_number")
    private Integer middleBoxAbleNumber;

    @ApiModelProperty(value = "小柜子可用数量 int")
    @TableField("small_box_able_number")
    private Integer smallBoxAbleNumber;

    @ApiModelProperty(value = "1.正常 2.暂停存件（只出不进，为了清空柜）3.停用 0.注销")
    @TableField("locker_status")
    private Integer lockerStatus;


}
