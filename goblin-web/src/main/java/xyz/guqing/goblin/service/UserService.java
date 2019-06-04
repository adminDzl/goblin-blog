package xyz.guqing.goblin.service;

import org.springframework.stereotype.Service;
import xyz.guqing.goblin.authentication.SecurityUserDetails;
import xyz.guqing.goblin.entity.SysPermission;
import xyz.guqing.goblin.entity.UserDTO;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 返回用户DTO对象
     */
    SecurityUserDetails findByUsername(String username);
    
    List<SysPermission> findPermissionByUsername(String username);
}
