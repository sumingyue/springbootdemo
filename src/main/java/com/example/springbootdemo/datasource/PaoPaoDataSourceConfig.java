package com.example.springbootdemo.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.springbootdemo.mapper.paopao", sqlSessionTemplateRef = "paopaoSqlSessionTemplate")
public class PaoPaoDataSourceConfig {


    @Bean(name = "paopaoDataSourceProperties")
    @ConfigurationProperties("spring.datasource.paopao")
    public DataSourceProperties paopaoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "paopaoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.paopao")
    public DataSource paopaoDataSource(@Qualifier("paopaoDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "paopaoSqlSessionFactory")
    public SqlSessionFactory paopaoSqlSessionFactory(@Qualifier("paopaoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/paopao/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "paopaoTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("paopaoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "paopaoSqlSessionTemplate")
    public SqlSessionTemplate paopaoSqlSessionTemplate(@Qualifier("paopaoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}
