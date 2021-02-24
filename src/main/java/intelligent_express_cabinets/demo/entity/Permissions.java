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
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("permissions")
@ApiModel(value="Permissions对象", description="")
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    @ApiModelProperty(value = "许可权限名称")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty(value = "许可权限URL")
    @TableField("permission_url")
    private String permissionUrl;

    @ApiModelProperty(value = "父权限id")
    @TableField("permission_parent_id")
    private Integer permissionParentId;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<Permissions> children;

    @ApiModelProperty(value = "角色列表")
    @TableField(exist = false)
    private List<Roles> roles;

}
