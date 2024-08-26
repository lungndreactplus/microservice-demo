package org.example.library.jpaconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
public abstract class BaseJpaConfiguration {

    public abstract String[] getPackagesToScan();
    public abstract Properties getHibernateProperties();
    protected abstract boolean useSchemaAwareDataSource();
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        if (useSchemaAwareDataSource()) {
            em.setDataSource(new SchemaAwareDataSource(dataSource));
        } else {
            em.setDataSource(dataSource);
        }
        em.setPackagesToScan(getPackagesToScan());

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = getHibernateProperties();
        em.setJpaProperties(properties);

        return em;
    }
    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
