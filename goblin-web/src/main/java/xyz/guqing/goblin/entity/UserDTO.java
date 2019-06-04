package xyz.guqing.goblin.entity;

import java.util.List;

public class UserDTO {
    private Integer id;
    
    private String username;
    
    private String email;
    
    private String password;
    
    private String avatar;
    
    private String nickname;
    
    private Integer roleId;
    
    private List<SysPermission> permissions;// 用户的权限
    
    private String province;
    
    private String city;// 城市
    
    private String district;// 行政区
    
    private String address;// 地址
    
    private String qq;
    
    private String wechat;
    
    private String github;
    
    private String weibo;
    
    private Boolean gender;
    
    private String profile;// 个性签名
    
    private Boolean status;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public Integer getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public List<SysPermission> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getQq() {
        return qq;
    }
    
    public void setQq(String qq) {
        this.qq = qq;
    }
    
    public String getWechat() {
        return wechat;
    }
    
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    
    public String getGithub() {
        return github;
    }
    
    public void setGithub(String github) {
        this.github = github;
    }
    
    public String getWeibo() {
        return weibo;
    }
    
    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }
    
    public Boolean getGender() {
        return gender;
    }
    
    public void setGender(Boolean gender) {
        this.gender = gender;
    }
    
    public String getProfile() {
        return profile;
    }
    
    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    public Boolean getStatus() {
        return status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
}
