package com.ccq.springbootkafka.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/20 15:50
 ***@Version 1.0.0
 ********************************/
@EnableTransactionManagement
@MapperScan("com.ccq.springbootkafka.mapper")
@Configuration
public class SqlSessionFactoryConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;

    private static final String TYPE_ALIAS_PACKAGE = "com.ccq.springbootkafka.domain";
    private static final String MYBATIS_MAPPER_LOCATION = "classpath:/mybatis/*.xml";


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    /**
     * 创建sqlSessionFactory
     * 设置mapper 映射路径
     * 设置datasource数据源
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        /** 设置datasource */
        sqlSessionFactoryBean.setDataSource(dataSource);
        /** 设置typeAlias 包扫描路径 */
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIAS_PACKAGE);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MYBATIS_MAPPER_LOCATION));
        // Configuration 对象，用于设置mybatis的settings属性。
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        // 开启javaBean 成员变量名映射为映射到数据库列名的时候，驼峰命名法到下划线分割命名法则的自动转换功能
        config.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(config);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
