package intelligent_express_cabinets.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("users")
@ApiModel(value="Users对象", description="用户")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id自增")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "账号（手机号）")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "名称")
    @TableField("user_real_name")
    private String userRealName;

    @ApiModelProperty(value = "地址")
    @TableField("user_address")
    private String userAddress;

    @ApiModelProperty(value = "状态码 0.未注销  1.已注销")
    @TableField("user_status")
    private Integer userStatus;

    @ApiModelProperty(value = "角色",hidden = true)
    @TableField(exist = false)
    private List<Roles> roles;


    @Override
    @JsonIgnore
    @ApiIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    @ApiIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @ApiIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    @ApiIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @ApiIgnore
    public boolean isEnabled() {
        return userStatus == 0;
    }
}
