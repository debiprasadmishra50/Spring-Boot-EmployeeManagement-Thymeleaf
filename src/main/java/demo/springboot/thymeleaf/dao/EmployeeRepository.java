package demo.springboot.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.springboot.thymeleaf.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	// No need for any code
	
	// add method to sort by lastname
	public List<Employee> findAllByOrderByLastNameAsc();
	
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
	// To look for the method name patterns
}
