package com.example.hiringmanager.department;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class DepartmentService {
    private final SessionFactory sessionFactory;

    @Autowired
    public DepartmentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // GET departments
    public List<Department> getDepartments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department", Department.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get departments", e);
        }
    }

    public Department getDepartmentById(Long departmentId) {
        try (Session session = sessionFactory.openSession()) {
            Department department = session.get(Department.class, departmentId);
            if (department == null) {
                throw new RuntimeException("Department with ID: " + departmentId + " doesn't exist");
            }
            return department;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get department by ID: " + departmentId, e);
        }
    }

    // ADD department
    public void addNewDepartment(Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to add a new department", e);
        }
    }

    // DELETE department
    public void deleteDepartment(Long departmentId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Department existingDepartment = session.get(Department.class, departmentId);
            if (existingDepartment == null) {
                throw new RuntimeException("Department with ID: " + departmentId + " doesn't exist");
            }
            session.delete(existingDepartment);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete the department", e);
        }
    }

    // UPDATE department
    @Transactional
    public void updateDepartment(Long departmentId, String departmentName, Long managerId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Department existingDepartment = session.get(Department.class, departmentId);
            if (existingDepartment == null) {
                throw new RuntimeException("Department with ID: " + departmentId + " doesn't exist");
            }

            if (departmentName != null) {
                existingDepartment.setDepartmentName(departmentName);
            }

            if (managerId != null) {
                existingDepartment.setManagerId(managerId);
            }

            session.update(existingDepartment);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update the department", e);
        }
    }
}
