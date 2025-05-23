package com.javatut.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.javatut.student.StudentJDBCTemplate;

@Configuration
@PropertySource("classpath:app.properties")
@Component("JDBCDBConfig")
public class JDBCDBConfig {

	@Value("${driverClassName}")
	private String driverClassName;
	
	@Value("${driver}")
	private String driver;
	
	@Value("${serverName}")
	private String serverName;
	
	@Value("${databaseName}")
	private String databaseName;
	
	@Value("${integratedSecurity}")
	private String integratedSecurity;
	
	@Value("${encrypt}")
	private String encrypt;
	
	@Value("${trustServerCertificate}")
	private String trustServerCertificate;
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }	
	
	public String createConnectionUrl() {		
		String url = driver + ":" + serverName + ";databaseName=" + databaseName + ";integratedSecurity=" + integratedSecurity + ";encrypt=" + encrypt + ";trustServerCertificate=" + trustServerCertificate;
		System.out.println("Connection URL = " + url);
		return url;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();	
		ds.setDriverClassName(driverClassName);
		ds.setUrl(createConnectionUrl());
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);		
	}
	
	@Bean
	public StudentJDBCTemplate studentJdbcTemplate(DataSource dataSource) {
		StudentJDBCTemplate temp = new StudentJDBCTemplate();
		temp.setDataSource(dataSource);
		return temp;
	}

}
