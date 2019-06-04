package xyz.guqing.goblin.authentication;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "guqing.security")
public class TokenProperties {
    private int expirationSeconds = 259200;//token真正过期时间默认3天
    private int tokenRefreshInterval = 257400; //token刷新时间
    private String salt = "_secret";
    private String headerName = "Authorization";
    private String tokenPrefix = "Bearer ";
    
    public int getExpirationSeconds() {
        return expirationSeconds;
    }
    
    public void setExpirationSeconds(int expirationSeconds) {
        this.expirationSeconds = expirationSeconds;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getHeaderName() {
        return headerName;
    }
    
    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
    
    public String getTokenPrefix() {
        return tokenPrefix;
    }
    
    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }
    
    public int getTokenRefreshInterval() {
        return tokenRefreshInterval;
    }
    
    public void setTokenRefreshInterval(int tokenRefreshInterval) {
        this.tokenRefreshInterval = tokenRefreshInterval;
    }
}
