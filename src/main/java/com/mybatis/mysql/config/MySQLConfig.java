package com.mybatis.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;


@MapperScan(value = "com.mybatis.mysql",
        annotationClass= MySQLConnMapper.class,
        sqlSessionFactoryRef="MySQLSessionFactory")
@Configuration
public class MySQLConfig {
    //When there are multiple beans of the same type,
    // give those beans a higher priority
    @Primary
    @Bean(name = "MySQLDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Primary
    @Bean(name = "MySQLSessionFactory")
    public SqlSessionFactory mySqlSessionFactory
            (@Qualifier("MySQLDataSource") DataSource mysqlDataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mysqlDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().
                        getResources("mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.mybatis.mysql.entity");
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "MySQLSessionTemplate")
    public SqlSessionTemplate mySqlSessionTemplate
            (@Qualifier("MySQLSessionFactory")
             SqlSessionFactory mySqlSessionFactory) {

        return new SqlSessionTemplate(mySqlSessionFactory);
    }


    @Bean(name = "MysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager PrimaryTransactionManager
            (@Qualifier("MySQLDataSource") DataSource mysqlDataSource) {

        return new DataSourceTransactionManager(mysqlDataSource);
    }
}