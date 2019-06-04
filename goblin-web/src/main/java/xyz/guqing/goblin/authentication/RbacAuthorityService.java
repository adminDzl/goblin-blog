package xyz.guqing.goblin.authentication;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xyz.guqing.goblin.entity.SysPermission;
import xyz.guqing.goblin.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("rbacauthorityservice")
public class RbacAuthorityService {
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();
        
        boolean hasPermission  = false;
        
        if (principal instanceof SecurityUserDetails) {
            SecurityUserDetails securityUserDetails = (SecurityUserDetails)principal;
            String username = ((UserDetails)principal).getUsername();
            //获取资源
            // 这些 url 都是要登录后才能访问，且其他的 url 都不能访问
            List<SysPermission> permissionList = securityUserDetails.getPermissions();
            //获取权限url
            Set<String> urls = new HashSet<>();
            for(SysPermission permission : permissionList){
                urls.add(permission.getUrl());
            }
    
            hasPermission = matcherUrl(request, urls);
    
            return hasPermission;
        } else {
            Set<String> urls = new HashSet<>();
//            urls.add("/goblin/article/list");
//            urls.add("/goblin/article/get/*");
            hasPermission = matcherUrl(request, urls);
            
            return hasPermission;
        }
    }
    
    /**
     * 匹配url规则
     * @param request
     * @param urls
     */
    private boolean matcherUrl(HttpServletRequest request, Set<String> urls) {
        boolean hasPermission = false;
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                hasPermission = true;
                break;
            }
        }
        
        return hasPermission;
    }
}
