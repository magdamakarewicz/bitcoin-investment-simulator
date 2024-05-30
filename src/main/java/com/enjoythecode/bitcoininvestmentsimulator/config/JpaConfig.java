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

/**
 * Configuration class for JPA settings.
 */
@PropertySource("classpath:application.properties")
@Configuration
public class JpaConfig {

    /**
     * The Environment field provides access to properties from the application's environment.
     * It is particularly useful for externalizing configuration and allows the application
     * to retrieve values from the `application.properties` file or other property sources.
     */
    @Autowired
    private Environment env;

    /**
     * Creates an instance of {@link LocalContainerEntityManagerFactoryBean}, which is used to set JPA settings
     * for an entity manager factory. The JPA settings depend on the given {@link JpaVendorAdapter} and {@link DataSource}.
     * The entity manager factory scans for classes in the specified package to create JPA entities.
     *
     * @param adapter    the {@link JpaVendorAdapter} that specifies which JPA implementation to use.
     * @param dataSource the {@link DataSource} that the entity manager factory uses to connect to a database.
     * @return an instance of {@link LocalContainerEntityManagerFactoryBean}.
     */
    @Bean
    @Profile({"prod", "dev", "test", "!prod & !dev & !test"})
    public LocalContainerEntityManagerFactoryBean createEmf(JpaVendorAdapter adapter, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(adapter);
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.enjoythecode.bitcoininvestmentsimulator.model");
        return emf;
    }

    /**
     * Creates an instance of {@link HibernateJpaVendorAdapter}, which is used to specify Hibernate-specific JPA settings.
     * The adapter is used by the entity manager factory to set database, SQL, and schema generation options.
     * This method is active only for the production and default profile.
     *
     * @return an instance of {@link HibernateJpaVendorAdapter}.
     */
    @Profile({"prod", "!prod & !dev & !test"})
    @Bean
    public HibernateJpaVendorAdapter createAdapterProd() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    /**
     * Creates an instance of {@link BasicDataSource}, which is used to provide database connection pooling
     * for the production environment. The data source contains the database URL, username, password, and pool configuration.
     * This method is active only for the production and default profile.
     *
     * @return an instance of {@link BasicDataSource}.
     */
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

    /**
     * Creates an instance of {@link HibernateJpaVendorAdapter}, which is used to specify Hibernate-specific JPA settings.
     * The adapter is used by the entity manager factory to set database, SQL, and schema generation options.
     * This method is active only for development and test profiles.
     *
     * @return an instance of {@link HibernateJpaVendorAdapter}.
     */
    @Profile({"dev", "test"})
    @Bean
    public HibernateJpaVendorAdapter createAdapterDevTest() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    /**
     * Creates an instance of {@link BasicDataSource}, which is used to provide database connection pooling
     * for the development and test environments. The data source contains the database URL, username, password,
     * and pool configuration. The values are retrieved from the `application.properties` file.
     * This method is active only for development and test profiles.
     *
     * @return an instance of {@link BasicDataSource}.
     */
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
