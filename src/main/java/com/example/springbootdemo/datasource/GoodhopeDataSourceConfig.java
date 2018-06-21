package com.example.springbootdemo.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@MapperScan(basePackages = "com.example.springbootdemo.mapper.goodhope", sqlSessionTemplateRef = "goodhopeSqlSessionTemplate")
public class GoodhopeDataSourceConfig {

    @Bean(name = "goodhopeDataSourceProperties")
    @Primary
    @ConfigurationProperties("spring.datasource.goodhope")
    public DataSourceProperties goodhopeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "goodhopeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.goodhope")
    @Primary
    public DataSource goodhopeDataSource(@Qualifier("goodhopeDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "goodhopeSqlSessionFactory")
    @Primary
    public SqlSessionFactory goodhopeSqlSessionFactory(@Qualifier("goodhopeDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/goodhope/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "goodhopeTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("goodhopeDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "goodhopeSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate goodhopeSqlSessionTemplate(@Qualifier("goodhopeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}
