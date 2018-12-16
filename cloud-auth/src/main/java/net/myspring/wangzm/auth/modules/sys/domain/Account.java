package net.myspring.wangzm.auth.modules.sys.domain;

import net.myspring.wangzm.common.domain.DataEntity;
import net.myspring.wangzm.common.domain.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Lenovo on 2018/1/2.
 */
@Entity
@Table(name = "hr_account")
public class Account extends IdEntity<Account> {
    private String loginName;
    private String password;

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
