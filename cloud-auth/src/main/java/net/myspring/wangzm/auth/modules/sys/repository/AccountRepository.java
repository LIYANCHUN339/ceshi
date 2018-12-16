package net.myspring.wangzm.auth.modules.sys.repository;

import net.myspring.wangzm.auth.modules.sys.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 2018/1/2.
 */
@Component
public interface AccountRepository extends CrudRepository<Account,String>{

    Account findByLoginName(String loginName);
}
