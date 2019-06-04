package xyz.guqing.goblin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.guqing.goblin.authentication.SecurityUserDetails;
import xyz.guqing.goblin.entity.*;
import xyz.guqing.goblin.mapper.*;
import xyz.guqing.goblin.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRolePermissionMapper rolePermissionMapper;
    @Resource
    private SysPermissionMapper permissionMapper;
    
    @Override
    public SecurityUserDetails findByUsername(String username) {
        List<User> userList = this.findUser(username);
        
        SecurityUserDetails userDetails = new SecurityUserDetails();
        if(userList != null && userList.size()==1){
            User user = userList.get(0);
            BeanUtils.copyProperties(user, userDetails);
            //查询用户权限信息
            List<Integer> roleIds = findUserRoleIds(user.getId());
            // 填充角色信息
            userDetails.setSysRoles(findRoleByUid(roleIds));
            //填充权限信息
            List<SysPermission> permissionList = this.findPermission(roleIds);
            userDetails.setPermissions(permissionList);
        }
        
        return userDetails;
    }
    
    @Override
    public List<SysPermission> findPermissionByUsername(String username) {
        return null;
    }
    
    /**
     * 查询用户关联的角色id
     * @param userId 用户id
     * @return 返回角色role的id集合
     */
    private List<Integer> findUserRoleIds(Integer userId){
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        SysUserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
        userRoleExampleCriteria.andSysUserIdEqualTo(userId);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectByExample(userRoleExample);
        
        //查询该用户有哪些角色，获取其id
        List<Integer> roleIds = new ArrayList<>();
        if(sysUserRoleList != null && sysUserRoleList.size()>0){
            for(SysUserRole sysUserRole : sysUserRoleList){
                roleIds.add(sysUserRole.getId());
            }
        }
       return roleIds;
    }
    
    /**
     * 根据角色id,即roleIds查询权限
     * @param roleIds 角色id
     * @return 返回权限集合
     */
    private List<SysPermission> findPermission(List<Integer> roleIds){
        SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andSysRoleIdIn(roleIds);
        List<SysRolePermission> sysRolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
        
        List<Integer> permissionIds = new ArrayList<>();
        for(SysRolePermission sysRolePermission: sysRolePermissionList) {
            permissionIds.add(sysRolePermission.getSysPermissionId());
        }
        
        //根据permission id查询用户权限
        SysPermissionExample sysPermissionExample = new SysPermissionExample();
        SysPermissionExample.Criteria permissionCriteria = sysPermissionExample.createCriteria();
        permissionCriteria.andIdIn(permissionIds);
        //查询权限
        return permissionMapper.selectByExample(sysPermissionExample);
    }
    
    /**
     * 查询用户的角色
     * @param roleIds 角色的id集合
     * @return 返回角色List集合
     */
    private List<SysRole> findRoleByUid(List<Integer> roleIds){
        // 根据该用户所拥有的角色id查询角色
        SysRoleExample roleExample = new SysRoleExample();
        SysRoleExample.Criteria roleExampleCriteria = roleExample.createCriteria();
        roleExampleCriteria.andAvailableEqualTo("1");
        roleExampleCriteria.andIdIn(roleIds);
        
        return sysRoleMapper.selectByExample(roleExample);
    }
    
    /**
     * 根据用户名和密码查询用户
     * @return 返回查询结果是一个List但结果只有两种一个或者为空
     */
    private List<User> findUser(String username){
        UserExample example = new UserExample();
        UserExample.Criteria criteria =  example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);
        
        return userList;
    }
}
