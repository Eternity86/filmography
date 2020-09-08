package ru.eternity074.filmography.config;

import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "ru.eternity074.filmography" })
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
public class HibernateConfig {

	private Environment env;

	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}

	private Properties hibernateProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		props.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));

		return props;
	}

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		ds.setUrl(env.getRequiredProperty("jdbc.url"));
		ds.setUsername(env.getRequiredProperty("jdbc.username"));
		ds.setPassword(env.getRequiredProperty("jdbc.password"));

		return ds;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("ru.eternity074.filmography.model");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		
		return transactionManager;
	}

}
