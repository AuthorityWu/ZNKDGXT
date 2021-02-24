package intelligent_express_cabinets.demo.config.security;

import intelligent_express_cabinets.demo.entity.Permissions;
import intelligent_express_cabinets.demo.entity.Roles;
import intelligent_express_cabinets.demo.service.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 */

@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IPermissionsService permissionsService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求url
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Permissions> permissions = permissionsService.getPermissionsWithRole();
        for (Permissions permission : permissions){
            //判断请求url与菜单角色是否匹配
            if (antPathMatcher.match(permission.getPermissionUrl(),requestUrl)){
                String[] str = permission.getRoles().stream().map(Roles::getRoleName).toArray(String[]::new);
                return SecurityConfig.createList(str);
            }
        }
        //没有匹配的url默认登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
