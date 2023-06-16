package com.example.hiringmanager.project;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProjectService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Project> getProjects() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Project", Project.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get projects", e);
        }
    }

    public Project getProjectById(Long projectId) {
        try (Session session = sessionFactory.openSession()) {
            Project project = session.get(Project.class, projectId);
            if (project == null) {
                throw new RuntimeException("Project with ID: " + projectId + " doesn't exist");
            }
            return project;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get project by ID: " + projectId, e);
        }
    }

    public void addNewProject(Project project) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(project);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to add a new project", e);
        }
    }


    public void deleteProject(Long projectId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Project existingProject = session.get(Project.class, projectId);
            if (existingProject == null) {
                throw new RuntimeException("Project with ID: " + projectId + " doesn't exist");
            }
            session.delete(existingProject);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete the project", e);
        }
    }

    @Transactional
    public void updateProject(Long projectId, String projectName, Date projectStartDate, Date projectDueDate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Project existingProject = session.get(Project.class, projectId);
            if (existingProject == null) {
                throw new RuntimeException("Project with ID: " + projectId + " doesn't exist");
            }

            if (projectName != null) {
                existingProject.setProjectName(projectName);
            }

            if (projectStartDate != null) {
                existingProject.setProjectStartDate(projectStartDate);
            }

            if (projectDueDate != null) {
                existingProject.setProjectDueDate(projectDueDate);
            }

            session.update(existingProject);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update the project", e);
        }
    }
}
