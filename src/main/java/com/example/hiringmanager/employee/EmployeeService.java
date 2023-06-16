package com.example.hiringmanager.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	private final SessionFactory sessionFactory;
	
	@Autowired
	public EmployeeService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// GET employees
	public List<Employee> getEmployees() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("FROM Employee", Employee.class).list();
		} catch (Exception e) {
			throw new RuntimeException("Failed to get employees", e);
		}
	}
	
	public Employee getEmployeeById(Long employeeId) {
		try (Session session = sessionFactory.openSession()) {
			Employee employee = session.get(Employee.class, employeeId);
			if(employee == null) {
				throw new RuntimeException("Employee with ID: " + employeeId + " doesn't exist");
			}
			return session.get(Employee.class, employeeId);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get employee by ID: " + employeeId, e);
		}
	}
	
	// ADD employee
	public void addNewEmployee(Employee employee) {
	    try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();
	        Query<Employee> query = session.createQuery("FROM Employee WHERE employeeId = :employeeId", Employee.class);
	        query.setParameter("employeeId", employee.getEmployeeId());
	        Employee existingEmployee = query.uniqueResult();
	        if (existingEmployee != null) {
	            throw new RuntimeException("Employee with ID: " + employee.getEmployeeId() + " already exists");
	        }
	        session.save(employee);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to add a new employee", e);
	    }
	}


	
	// DELETE employee
	public void deleteEmployee(Long employeeId) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Employee existingEmployee = session.get(Employee.class, employeeId);
			if (existingEmployee == null) {
				throw new RuntimeException("Employee with ID: " + employeeId + " doesn't exist");
			}
			session.delete(existingEmployee);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete the employee", e);
		}
	}
	
	// UPDATE employee
	@Transactional
	public void updateEmployee(Long employeeId, String name, int departmentId, String role, int salary) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Employee existingEmployee = session.get(Employee.class, employeeId);
			if (existingEmployee == null) {
				throw new RuntimeException("Employee with ID: " + employeeId + " doesn't exists");
			}	
	        
	        if (name != null) {
	            String[] nameParts = name.trim().split("\\s+");
	            StringBuilder modifiedName = new StringBuilder(nameParts[0]);
	            if (nameParts.length > 1) {
	                String secondWord = nameParts[1];
	                modifiedName.append(" ").append(secondWord.charAt(0)).append(".");
	            }
	            existingEmployee.setName(modifiedName.toString());
	        }
	        
	        if (departmentId != 0) {
	            existingEmployee.setDepartmentId(departmentId);
	        }
	        
	        if (role != null) {
	            existingEmployee.setRole(role);
	        }
	        
	        if (salary < 22000) {
	        	throw new RuntimeException("Employee salary cannot be smaller than 22000");
	        }
	        existingEmployee.setSalary(salary);

	        session.update(existingEmployee);
	        session.getTransaction().commit();
		} catch (Exception e) {
			throw new RuntimeException("Failed to update the employee", e);
		}
	}
	
	// CALCULATE average salary by department ID
	public double calculateAvgSalary(int departmentId) {
	    try (Session session = sessionFactory.openSession()) {
	    	// Get count of employees with given department id
	        Query<Long> countQuery = session.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.departmentId = :departmentId", Long.class);
	        countQuery.setParameter("departmentId", departmentId);
	        Long employeeCount = countQuery.getSingleResult();
	        // check if given department has any employees
	        if (employeeCount == 0) {
	            throw new RuntimeException("No employees found for department ID: " + departmentId);
	        }
	        
	        Query<Double> query = session.createQuery("SELECT AVG(e.salary) FROM Employee e WHERE e.departmentId = :departmentId", Double.class);
	        query.setParameter("departmentId", departmentId);
	        return query.getSingleResult();
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to calculate the average salary for department ID: " + departmentId, e);
	    }
	}

}
