package com.cihatguven.dbrouting.master.database.config;

import com.cihatguven.dbrouting.school.database.DbSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = "com.cihatguven.dbrouting.master",
        entityManagerFactoryRef = "masterEntityManager",
        transactionManagerRef = "masterTransactionManager"
)
public class MasterDbConfig {

    private final DbSettings dbSettings;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean masterEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(masterDataSource());
        em.setPackagesToScan("com.cihatguven.dbrouting.master");
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(HibernatePropertiesMaster.get());

        return em;
    }

    @Bean
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .url(dbSettings.getMasterDatabaseName())
                .username(dbSettings.getUsername())
                .password(dbSettings.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager masterTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(masterEntityManager().getObject());
        return transactionManager;
    }
}