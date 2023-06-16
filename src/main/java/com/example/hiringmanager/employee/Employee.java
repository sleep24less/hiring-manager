package com.example.hiringmanager.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Employee {
	
	@Id
	@SequenceGenerator (
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
	)
	private int employeeId;
	private String name;
	private int departmentId;
	private int projectId;
	private String role;
	
	// with ID
	public Employee(int employeeId, String name, int departmentId, int projectId, String role, int salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.departmentId = departmentId;
		this.projectId = projectId;
		this.role = role;
		this.salary = salary;
	}
	
	// without ID
	public Employee(String name, int departmentId, int projectId, String role, int salary) {
		super();
		this.name = name;
		this.departmentId = departmentId;
		this.projectId = projectId;
		this.role = role;
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
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
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	private int salary;

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", departmentId=" + departmentId
				+ ", projectId=" + projectId + ", role=" + role + ", salary=" + salary + "]";
	}
	
	
	
}
