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
@TableName("users")
@ApiModel(value="Users对象", description="")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "账号（手机号）")
    @TableField("user_account")
    private String userAccount;

    @ApiModelProperty(value = "用户密码")
    @TableField("user_password")
    private String userPassword;

    @ApiModelProperty(value = "名称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "地址")
    @TableField("user_address")
    private String userAddress;

    @ApiModelProperty(value = "状态码 0.未注销  1.已注销")
    @TableField("user_status")
    private Integer userStatus;


}
