package net.myspring.wangzm.auth.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public DataSource oauth2DataSource() {
        return getDataSource("spring.datasource.basic");
    }

    private DataSource getDataSource(String prefix) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty(prefix + ".driver-class-name"));
        dataSource.setUrl(environment.getProperty(prefix + ".url"));
        dataSource.setUsername(environment.getProperty(prefix + ".username"));
        dataSource.setPassword(environment.getProperty(prefix + ".password"));
        dataSource.setMaxActive(200);
        dataSource.setMinIdle(5);
        dataSource.setInitialSize(0);
        dataSource.setRemoveAbandonedTimeout(120);
        dataSource.setLogAbandoned(true);
        return dataSource;
    }
}
