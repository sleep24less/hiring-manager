package com.example.hiringmanager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping(path = "{projectId}")
    public Project getProjectById(@PathVariable("projectId") Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @PostMapping
    public void addNewProject(@RequestBody Project project) {
        projectService.addNewProject(project);
    }

    @DeleteMapping(path = "{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PutMapping(path = "{projectId}")
    public void updateProject(
            @PathVariable("projectId") Long projectId,
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) Date projectStartDate,
            @RequestParam(required = false) Date projectDueDate) {
        projectService.updateProject(projectId, projectName, projectStartDate, projectDueDate);
    }
}
