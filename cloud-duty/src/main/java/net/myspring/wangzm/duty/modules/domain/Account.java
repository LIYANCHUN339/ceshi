package net.myspring.wangzm.duty.modules.domain;

/**
 * Created by Lenovo on 2018/1/2.
 */
public class Account  {
    private String id;
    private String loginName;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}