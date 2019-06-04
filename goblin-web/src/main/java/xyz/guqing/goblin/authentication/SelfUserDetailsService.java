package xyz.guqing.goblin.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.guqing.goblin.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Component
public class SelfUserDetailsService implements UserDetailsService {
    @Resource
    private UserServiceImpl userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //构建用户信息的逻辑(取数据库/LDAP等用户信息)
        SecurityUserDetails userDetails = userService.findByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        userDetails.setPassword(new BCryptPasswordEncoder().encode(userDetails.getPassword()));
       
        return userDetails;
    }
}
