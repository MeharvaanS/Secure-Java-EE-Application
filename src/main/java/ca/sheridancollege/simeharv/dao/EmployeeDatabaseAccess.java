package ca.sheridancollege.simeharv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.simeharv.model.Employee;

@Repository
public class EmployeeDatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public long addEmployee(Employee employee) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String insert= "INSERT INTO employees (employeeName, employeeEmail, employeePhone)"
					+ " VALUES (:employeeName, :employeeEmail, :employeePhone);";
				
		namedParameters.addValue("employeeName", employee.getEmployeeName());
		namedParameters.addValue("employeeEmail", employee.getEmployeeEmail());
		namedParameters.addValue("employeePhone", employee.getEmployeePhone());
		
		int rowsAffected = jdbc.update(insert, namedParameters);
		return rowsAffected;
		}
	
	public long updateEmployeeData(Employee employee) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String updateEmployee =
		"update employees Set employeeName = :employeeName, employeeEmail = :employeeEmail, employeePhone = :employeePhone "
		+ "where employeeNumber = :employeeNumber;";
		
		namedParameters.addValue("employeeNumber",employee.getEmployeeNumber());
		namedParameters.addValue("employeeName", employee.getEmployeeName());
		namedParameters.addValue("employeeEmail", employee.getEmployeeEmail());
		namedParameters.addValue("employeePhone", employee.getEmployeePhone());
		
		int rowsUpdated = jdbc.update(updateEmployee, namedParameters);
		return rowsUpdated;
		}
	
	public long deleteEmployeeData(int employeeNumber) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String deleteEmployee =
		"DELETE FROM employees WHERE employeeNumber = :employeeNumber;";
		
		namedParameters.addValue("employeeNumber",employeeNumber);
		
		int rowsUpdated = jdbc.update(deleteEmployee, namedParameters);
		return rowsUpdated;
		}
	
	public List<Employee> selectEmployees() {
		MapSqlParameterSource namedParameters =
		new MapSqlParameterSource();
		String query = "SELECT * FROM employees";
		List<Employee> employees = jdbc.query(query, namedParameters,
		new BeanPropertyRowMapper<Employee>(Employee.class));
		return employees;
		}
	
	public List<Employee> selectEmployeeByEmployeePhone(long employeePhone) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String employeeByEmployeePhone =
		"SELECT * FROM employees where employeePhone = :employeePhone";
		namedParameters.addValue("employeePhone", employeePhone);
		List<Employee> employees =jdbc.query(employeeByEmployeePhone, namedParameters,
		new BeanPropertyRowMapper<Employee>(Employee.class));
		
		return employees;
	}
	
	public List<Employee> selectEmployeeByEmployeeName(String employeeName) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String employeeByEmployeeName = "SELECT * FROM employees where employeeName "
				+ "= :employeeName";
		namedParameters.addValue("employeeName", employeeName);
		List<Employee> employees =jdbc.query(employeeByEmployeeName, namedParameters,
		new BeanPropertyRowMapper<Employee>(Employee.class));
		
		return employees;
	}
	
	public List<Employee> selectEmployeeByEmployeeEmail(String employeeEmail) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String employeeByEmployeeEmail =
		"SELECT * FROM employees where employeeEmail = :employeeEmail";
		namedParameters.addValue("employeeEmail", employeeEmail);
		List<Employee> employees =jdbc.query(employeeByEmployeeEmail, namedParameters,
		new BeanPropertyRowMapper<Employee>(Employee.class));
		
		return employees;
	}
	
	public Employee selectEmployeeByEmployeeNumber(int employeeNumber) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		Employee employee;
		String employeeByEmployeeNumber =
		"SELECT * FROM employees where employeeNumber = :employeeNumber";
		namedParameters.addValue("employeeNumber", employeeNumber);
		List<Employee> employees =jdbc.query(employeeByEmployeeNumber, namedParameters,
		new BeanPropertyRowMapper<Employee>(Employee.class));
		if (employees.size() > 0 ){
		employee = employees.get(0);
		}
		else {
		employee = new Employee();
		}
		return employee;
		}
}
