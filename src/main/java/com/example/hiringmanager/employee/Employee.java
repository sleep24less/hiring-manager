package com.example.hiringmanager.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    @SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
	private Long employeeId;
	private String name;
	private int departmentId;
	private String role;
	private int salary;
	
	public Employee() {

	}

	// with ID
	public Employee(Long employeeId, String name, int departmentId, String role, int salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.departmentId = departmentId;
		this.role = role;
		this.salary = salary;
	}
	
	// without ID
	public Employee(String name, int departmentId, String role, int salary) {
		super();
		this.name = name;
		this.departmentId = departmentId;
		this.role = role;
		this.salary = salary;
	}

	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", departmentId=" + departmentId + ", role="
				+ role + ", salary=" + salary + "]";
	}

}
