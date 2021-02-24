package intelligent_express_cabinets.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户登录视图类
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserLoginParam对象", description="")
public class UserLoginParam {

    @ApiModelProperty(value = "用户账号",required = true)
    private String username;

    @ApiModelProperty(value = "用户密码",required = true)
    private String password;
}
