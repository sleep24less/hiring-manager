package com.example.hiringmanager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {
        // Create Hibernate configuration
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(com.example.hiringmanager.employee.Employee.class);

        return configuration.buildSessionFactory();
    }
}
