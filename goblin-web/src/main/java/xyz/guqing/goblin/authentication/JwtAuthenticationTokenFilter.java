package xyz.guqing.goblin.authentication;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.guqing.goblin.common.JwtTokenUtil;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenProperties tokenProperties;
    
    @Autowired
    SelfUserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(tokenProperties.getHeaderName());
        
        if (authHeader != null && authHeader.startsWith(tokenProperties.getTokenPrefix())) {
            final String authToken = authHeader.substring(tokenProperties.getTokenPrefix().length());
            String tokenObject = JwtTokenUtil.parseToken(authToken, tokenProperties.getSalt());
            Token token = JSONObject.toJavaObject(JSONObject.parseObject(tokenObject), Token.class);
            
            if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                String username = token.getUsername();
                
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
                if (shouldTokenRefresh(token.getIssuedAt())) {
                    //如果token过期，刷新token
                    System.out.println("token 过期。。。。");
                    refreshToken(response, username);
                }
                
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        
        chain.doFilter(request, response);
    }
    
    /**
     * 判断是否需要刷新TOKEN
     *
     * @param issueAt token签发日期
     * @return 是否需要刷新TOKEN
     */
    private boolean shouldTokenRefresh(Date issueAt) {
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenProperties.getTokenRefreshInterval()).isAfter(issueTime);
    }
    
    /**
     * 签发token证书
     * @param response 响应
     * @param username 当前用户名
     */
    private void refreshToken(HttpServletResponse response, String username) {
        //token 过期
        Token token = new Token();
        token.setUsername(username);
        token.setIssuedAt(new Date());
        
        // 生成token，有效时间3天
        String jwtToken = JwtTokenUtil.generateToken(JSONObject.toJSONString(token), tokenProperties.getExpirationSeconds(), tokenProperties.getSalt());
        response.setHeader(tokenProperties.getHeaderName(), tokenProperties.getTokenPrefix()+jwtToken);
    }
}
