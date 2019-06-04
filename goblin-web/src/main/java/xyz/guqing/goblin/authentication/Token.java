package xyz.guqing.goblin.authentication;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {
    private String username;
    private Date issuedAt;//证书的签发时间
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Date getIssuedAt() {
        return issuedAt;
    }
    
    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
}
