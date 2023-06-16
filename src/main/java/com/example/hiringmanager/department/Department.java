package com.example.hiringmanager.department;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Department {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
    @SequenceGenerator(name = "department_sequence", sequenceName = "department_sequence", allocationSize = 10, initialValue = 10)
	@Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "manager_id")
    private Long managerId;

    private List<Integer> listOfEmployees;

	public Department() {

	}

	public Department(Long departmentId, String departmentName, Long managerId, List<Integer> listOfEmployees) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
		this.listOfEmployees = listOfEmployees;
	}

	public Department(String departmentName, Long managerId, List<Integer> listOfEmployees) {
		super();
		this.departmentName = departmentName;
		this.managerId = managerId;
		this.listOfEmployees = listOfEmployees;
	}



	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public List<Integer> getListOfEmployees() {
		return listOfEmployees;
	}

	public void setListOfEmployees(List<Integer> listOfEmployees) {
		this.listOfEmployees = listOfEmployees;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId="
				+ managerId + ", listOfEmployees=" + listOfEmployees + "]";
	}
    
    	
	

}
