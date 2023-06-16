package com.example.hiringmanager.project;

import java.time.LocalDate;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Project {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_sequence")
    @SequenceGenerator(name = "project_sequence", sequenceName = "project_sequence", allocationSize = 1, initialValue = 1)
	@Column(name = "project_id")
	private Long projectId;
	private String projectName;
	@Transient
	private Date projectStartDate;
	private Date projectDueDate;
	
	
	public Project() {

	}
	public Project(String projectName, Date projectStartDate, Date projectDueDate) {
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectDueDate = projectDueDate;
	}
	public Project(Long projectId, String projectName, Date projectStartDate, Date projectDueDate) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectDueDate = projectDueDate;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public LocalDate getProjectStartDate() {
		return LocalDate.now();
	}
	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public Date getProjectDueDate() {
		return projectDueDate;
	}
	public void setProjectDueDate(Date projectDueDate) {
		this.projectDueDate = projectDueDate;
	}
	
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectStartDate="
				+ projectStartDate + ", projectDueDate=" + projectDueDate + "]";
	}

}
