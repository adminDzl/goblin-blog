package xyz.guqing.goblin.common;

import java.io.Serializable;

/**
 * 返回结果类，包含状态码成功信息及响应结果
 */
public class Result implements Serializable {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;
    private String token;// 登陆token
    
    public Result() {
    }
    
    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
    public Result(boolean flag, Integer code, String message, Object data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public Result(boolean flag, Integer code, String message, Object data, String token) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
        this.token = token;
    }
    
    /**
     * 返回成功消息
     * @return Result
     */
    public static Result success() {
        return new Result(true, StatusCode.OK, "成功");
    }
    public static Result success(Object data) {
        return new Result(true, StatusCode.OK, "成功",data);
    }
    /**
     * 返回失败消息
     * @return Result
     */
    public static Result error() {
        return new Result(true, StatusCode.ERROR, "失败");
    }
    
    public static Result error(String message) {
        return new Result(true, StatusCode.ERROR, "失败",message);
    }
    /**
     * 返回登录失败的消息：用户名或密码错误
     * @return Result
     */
    public static Result loginError() {
        return new Result(true, StatusCode.LOGINERROR, "用户名或密码错误");
    }
    
    /**
     * 返回权限不足
     * @return Result
     */
    public static Result accessError() {
        return new Result(true, StatusCode.ACCESSERROR, "权限不足");
    }
    
    public static Result accessError(String message) {
        return new Result(true, StatusCode.ACCESSERROR, message);
    }
    
    /**
     * 返回远程调用失败
     * @return Result
     */
    public static Result remoteError() {
        return new Result(true, StatusCode.REMOTEERROR, "远程调用失败");
    }
    
    /**
     * 返回重复操作
     * @return Result
     */
    public static Result repError() {
        return new Result(true, StatusCode.REPERROR, "重复操作");
    }
    
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}
