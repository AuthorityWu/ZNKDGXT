package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.common.returnBean;
import intelligent_express_cabinets.demo.config.security.JwtTokenUtil;
import intelligent_express_cabinets.demo.dao.RolesMapper;
import intelligent_express_cabinets.demo.dao.UsersMapper;
import intelligent_express_cabinets.demo.entity.Roles;
import intelligent_express_cabinets.demo.entity.Users;
import intelligent_express_cabinets.demo.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public returnBean login(String username, String password, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails==null || !passwordEncoder.matches(password,userDetails.getPassword())){
            return returnBean.error("用户名或密码不正确!");
        }
        if (!userDetails.isEnabled()){
            return returnBean.error("此账户已经被禁用，清联系管理员!");
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails
                ,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return returnBean.success("登录成功",tokenMap);
    }

    /*@Override
    public Users getUserByUsername(String username) {
        return usersMapper.selectOne(new QueryWrapper<Users>().eq("username",username));
    }*/
    @Override
    public Users getUserByUsername(String username) {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return this.getOne(queryWrapper,false);
        //return usersMapper.selectOne(new QueryWrapper<Users>().eq("username",username));
    }

    @Override
    public List<Roles> getRoles(Integer userId) {
        return rolesMapper.getRoles(userId);
    }
}
