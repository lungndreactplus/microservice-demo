package org.example.multitenant.config;

import jakarta.persistence.EntityManagerFactory;
import org.example.library.jpaconfig.BaseJpaConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:persistence-multiple-db.properties"})
@EnableJpaRepositories(
        basePackages  = "org.example.multitenant.repository.dao.testproduct",
        entityManagerFactoryRef = "testproductEntityManagerFactory",
        transactionManagerRef = "testproductTransactionManager"
)
public class TestProductJpaConfiguration extends BaseJpaConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean testproductEntityManagerFactory(
            @Qualifier("testproductDataSource") DataSource dataSource
    ) {
        return super.entityManagerFactory(dataSource);
    }

    @Bean
    public PlatformTransactionManager testproductTransactionManager(
            @Qualifier("testproductEntityManagerFactory") LocalContainerEntityManagerFactoryBean testproductEntityManagerFactory) {
        return super.transactionManager(testproductEntityManagerFactory);
    }

    @Override
    public String[] getPackagesToScan() {
        return new String[]{"org.example.multitenant.model.testproduct"};
    }

    @Override
    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }
    @Override
    protected boolean useSchemaAwareDataSource() {
        return true;
    }
}
