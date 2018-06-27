package fluffybunny.dectectivebunny.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.commons.dbcp2.BasicDataSource;

import fluffybunny.dectectivebunny.DectectiveRunner;
import fluffybunny.dectectivebunny.entity.Anime;
import fluffybunny.dectectivebunny.entity.AnimeRequest;

@Configuration
@ComponentScan({"fluffybunny.dectectivebunny"})
@EnableTransactionManagement
//@ComponentScan(basePackages="fluffybunny.malbunny")
//@ComponentScans(value = {@ComponentScan("Fluffybunny.bunnytets3.dao"),@ComponentScan("Fluffybunny.bunnytets3.entity"),@ComponentScan("Fluffybunny.bunnytets3.service")})
public class HibernateConf {

 
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
    	System.out.println("Session Factory");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("fluffybunny.dectectivebunny.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setAnnotatedClasses(Anime.class, AnimeRequest.class);
        return sessionFactory;
    }
 
    
    @Bean
    public DataSource dataSource() {
    	System.out.println("Data Source");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        //dataSource.setUrl("jdbc:postgresql://localhost:5432/animu");
        dataSource.setUrl("jdbc:postgresql://animebunnydb.cbd3nsrkhe6w.us-west-1.rds.amazonaws.com:5432/animebunnydb");
        //dataSource.setUrl("#{ 'jdbc:postgresql://' + @dbUrl.getHost() + ':' + @dbUrl.getPort() + @dbUrl.getPath() }\");
        dataSource.setUsername("animebunny");
        dataSource.setPassword("dachibunny");

        
   /*     dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/anime");
        dataSource.setUsername("root");
        dataSource.setPassword("root");*/
        
        
        return dataSource;
    }
/*
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
    	System.out.println("Hibernate Transaction Managery");
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
 */
    
 /*   @Bean 
    public DectectiveRunner dectectiveRunner(){
       return new DectectiveRunner();
    } */
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
    
    private final Properties hibernateProperties() {
    	System.out.println("Hibernate Properties");
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
          "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        //hibernateProperties.setProperty("hibernate.show_sql", "true");
        //hibernateProperties.setProperty("hibernate.format_sql", "true");
        return hibernateProperties;
    }
}