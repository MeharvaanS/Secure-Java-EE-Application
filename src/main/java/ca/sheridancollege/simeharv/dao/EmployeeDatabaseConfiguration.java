package ca.sheridancollege.simeharv.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class EmployeeDatabaseConfiguration {
	
	//Used in the CoursesDatabaseAccess class to submit JDBC parametric query Strings
	@Bean
	public NamedParameterJdbcTemplate
	namedParemterJdbcTemplate(DataSource dataSource) {
	return new NamedParameterJdbcTemplate(dataSource);
	}

}
