package com.enjoythecode.bitcoininvestmentsimulator.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@PropertySource("classpath:application.properties")
@Configuration
public class JpaConfig {

    @Autowired
    private Environment env;

    @Bean
    @Profile({"prod", "dev", "test", "!prod & !dev & !test"})
    public LocalContainerEntityManagerFactoryBean createEmf(JpaVendorAdapter adapter, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(adapter);
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.enjoythecode.bitcoininvestmentsimulator.model");
        return emf;
    }

    @Profile({"prod", "!prod & !dev & !test"})
    @Bean
    public HibernateJpaVendorAdapter createAdapterProd() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Profile({"prod", "!prod & !dev & !test"})
    @Bean
    public BasicDataSource createDataSourceProd() {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:mysql://localhost:3306/bitcoininvestmentdb?useSSL=false&serverTimezone=CET");
        bds.setUsername("root");
        bds.setPassword("root");
        bds.setMinIdle(5);
        bds.setMaxIdle(20);
        return bds;
    }

    @Profile({"dev", "test"})
    @Bean
    public HibernateJpaVendorAdapter createAdapterDevTest() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Profile({"dev", "test"})
    @Bean
    public BasicDataSource createDataSourceDevTest() {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl(env.getProperty("jdbc.url"));
        bds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        bds.setUsername(env.getProperty("jdbc.user"));
        bds.setPassword(env.getProperty("jdbc.pass"));
        bds.setMinIdle(5);
        bds.setMaxIdle(20);
        return bds;
    }

}
