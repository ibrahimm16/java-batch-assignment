package hibernate;

import lombok.Data;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.sql.DataSource;
import java.util.Properties;

@Data
public class DBConfig {

    private DataSource dataSource;
    private Properties properties;
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    PersistenceUnitUtil unitUtil;

    DBConfig() {
        setDataSource();
        setProperties();
        setEntityManagerFactory(dataSource, properties);
        entityManager = factory.createEntityManager();
        unitUtil = factory.getPersistenceUnitUtil();
    }

    private void setDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test1");
        this.dataSource = dataSource;
    }

    private void setProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        this.properties = properties;
    }

    private void setEntityManagerFactory(DataSource source, Properties properties) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(source);
        factory.setPackagesToScan("hibernate/entities");
        factory.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        factory.setJpaProperties(properties);
        factory.setPersistenceUnitName("demo-unit");
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.afterPropertiesSet();
        this.factory = factory.getObject();
    }
}
