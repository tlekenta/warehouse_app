package pl.edu.wat.warehouse_app.zrodlo_system.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "zrodloSystemEntityManagerFactory",
        transactionManagerRef = "zrodloSystemTransactionManager",
        basePackages = "pl.edu.wat.warehouse_app.zrodlo_system.repository"
)
public class ZrodloSystemDbConfig {

    @Bean(name = "zrodloSystemDataSource")
    @ConfigurationProperties(prefix = "zrodlo-system.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "zrodloSystemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder pBuilder,
                                                                       @Qualifier("zrodloSystemDataSource") DataSource pDataSource,
                                                                       @Value("${zrodlo-system.clear}") boolean pClearDatabase) {
        HashMap<String, Object> properties = new HashMap<>();
        if(pClearDatabase)
            properties.put("hibernate.hbm2ddl.auto", "create");
        return pBuilder
                .dataSource(pDataSource)
                .properties(properties)
                .packages("pl.edu.wat.warehouse_app.zrodlo_system.model")
                .build();
    }

    @Bean(name = "zrodloSystemTransactionManager")
    public PlatformTransactionManager testTransactionManager(@Qualifier("zrodloSystemEntityManagerFactory")EntityManagerFactory pEntityManagerFactory) {
        return new JpaTransactionManager(pEntityManagerFactory);
    }

}
