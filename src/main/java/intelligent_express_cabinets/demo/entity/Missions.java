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
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("missions")
@ApiModel(value="Missions对象", description="")
public class Missions implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "mission_id", type = IdType.AUTO)
    private Integer missionId;

    @ApiModelProperty(value = "类型* int  1.维修柜  2.安装柜  3.检修柜 ")
    @TableField("mission_type")
    private Integer missionType;

    @ApiModelProperty(value = "执行人ID 外键 int")
    @TableField("operator_id")
    private Integer operatorId;

    @ApiModelProperty(value = "柜机ID 外键 int")
    @TableField("locker_id")
    private Integer lockerId;

    @ApiModelProperty(value = "内容 string")
    @TableField("mission_content")
    private String missionContent;

    @ApiModelProperty(value = "1.低 2.中 3.高 ")
    @TableField("mission_urgency")
    private Integer missionUrgency;

    @ApiModelProperty(value = "生成日期 date")
    @TableField("mission_start_time")
    private LocalDateTime missionStartTime;

    @ApiModelProperty(value = "结束日期 date")
    @TableField("mission_end_data")
    private LocalDateTime missionEndData;

    @ApiModelProperty(value = "状态* int   1.未接收 2.已接收 3.完成 4.任务不可见(专柜员删除，管理员可见)")
    @TableField("mission_status")
    private Integer missionStatus;


}
