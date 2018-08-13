package pl.edu.wat.warehouse_app.zrodlo_pos.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "zrodloPosEntityManagerFactory",
        transactionManagerRef = "zrodloPosTransactionManager",
        basePackages = "pl.edu.wat.warehouse_app.zrodlo_pos.repository"
)
public class ZrodloPosDbConfig {

    @Primary
    @Bean(name = "zrodloPosDataSource")
    @ConfigurationProperties(prefix = "zrodlo-pos.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "zrodloPosEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder pBuilder,
                                                                       @Qualifier("zrodloPosDataSource") DataSource pDataSource,
                                                                       @Value("${zrodlo-pos.clear}") boolean pClearDatabase) {
        HashMap<String, Object> properties = new HashMap<>();
        if(pClearDatabase)
            properties.put("hibernate.hbm2ddl.auto", "create");
        return pBuilder
                .dataSource(pDataSource)
                .properties(properties)
                .packages("pl.edu.wat.warehouse_app.zrodlo_pos.model")
                .build();
    }

    @Primary
    @Bean(name = "zrodloPosTransactionManager")
    public PlatformTransactionManager testTransactionManager(@Qualifier("zrodloPosEntityManagerFactory")EntityManagerFactory pEntityManagerFactory) {
        return new JpaTransactionManager(pEntityManagerFactory);
    }

}
