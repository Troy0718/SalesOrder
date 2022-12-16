package main.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("main")
@PropertySource("/WEB-INF/resources/database.properties") 
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="main.repository")

public class DatabaseConfig {
	
	@Autowired
	private Environment enviroment;
	
	@Bean
	public DataSource getDataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName(enviroment.getProperty("jdbc.driver"));
		  dataSource.setUrl(enviroment.getProperty("jdbc.url"));
		  dataSource.setUsername(enviroment.getProperty("jdbc.username"));
		  dataSource.setPassword(enviroment.getProperty("jdbc.password"));
		  
		return dataSource;
	}
	
	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(getDataSource());
		localSessionFactoryBean.setPackagesToScan(new String[] {"main"});
		localSessionFactoryBean.setHibernateProperties(hibernateProperties());	
		return localSessionFactoryBean;
	}
	
	
	private final Properties hibernateProperties() {
		Properties prperties = new Properties();
		prperties.put("hibernate.show_sql", enviroment.getProperty("hibernate.show_sql"));
		prperties.put("hibernate.dialect", enviroment.getProperty("hibernate.dialect"));
		prperties.put("hibernate.hbm2ddl.auto", enviroment.getProperty("hibernate.hbm2ddl.auto"));
		return prperties;
	}
	
	 @Bean(name = "transactionManager")
	 public HibernateTransactionManager getTransactionManager() {
		 HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		 transactionManager.setSessionFactory(sessionFactoryBean().getObject());
		return transactionManager;
	 }
	
}
