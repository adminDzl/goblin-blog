package xyz.guqing.goblin.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.guqing.goblin.authentication.SecurityUserDetails;
import xyz.guqing.goblin.service.impl.UserServiceImpl;

import javax.annotation.Resource;

@Component
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserServiceImpl userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUserDetails userDetails = userService.findByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return userDetails;
    }
}
