package pl.edu.wat.warehouse_app.stage.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
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
        entityManagerFactoryRef = "stageEntityManagerFactory",
        transactionManagerRef = "stageTransactionManager",
        basePackages = "pl.edu.wat.warehouse_app.stage.model"
)
public class StageDbConfig {

    @Bean(name = "stageDataSource")
    @ConfigurationProperties(prefix = "stage.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "stageEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder pBuilder, @Qualifier("stageDataSource") DataSource pDataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        return pBuilder
                .dataSource(pDataSource)
                .properties(properties)
                .packages("pl.edu.wat.warehouse_app.stage.model")
                .build();
    }

    @Bean(name = "stageTransactionManager")
    public PlatformTransactionManager testTransactionManager(@Qualifier("stageEntityManagerFactory")EntityManagerFactory pEntityManagerFactory) {
        return new JpaTransactionManager(pEntityManagerFactory);
    }

}
