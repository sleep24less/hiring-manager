package com.example.hiringmanager.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping
    public void addNewDepartment(@RequestBody Department department) {
        departmentService.addNewDepartment(department);
    }

    @DeleteMapping(path = "{departmentId}")
    public void deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }

    @PutMapping(path = "{departmentId}")
    public void updateDepartment(
            @PathVariable("departmentId") Long departmentId,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) Long managerId) {
        departmentService.updateDepartment(departmentId, departmentName, managerId);
    }
}
