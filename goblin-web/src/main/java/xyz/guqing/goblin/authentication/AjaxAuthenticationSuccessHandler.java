package xyz.guqing.goblin.authentication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.guqing.goblin.common.JwtTokenUtil;
import xyz.guqing.goblin.common.Result;
import xyz.guqing.goblin.common.StatusCode;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private TokenProperties tokenProperties;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        Token token = new Token();
        token.setUsername(userDetails.getUsername());
        token.setIssuedAt(new Date());
        
        // 生成token，有效时间3天
        String jwtToken = JwtTokenUtil.generateToken(JSONObject.toJSONString(token), tokenProperties.getExpirationSeconds(), tokenProperties.getSalt());
        
        Result result = new Result();
        result.setCode(StatusCode.OK);
        result.setMessage("登陆成功");
        result.setFlag(true);
        result.setToken(tokenProperties.getTokenPrefix()+jwtToken);
        
        httpServletResponse.getWriter().write(JSONObject.toJSONString(result));
    }
}

