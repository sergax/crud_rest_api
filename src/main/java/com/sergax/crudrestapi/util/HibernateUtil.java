package com.sergax.crudrestapi.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable th) {
            System.err.println("Initial SessionFactory creation failed " + th);
            throw new ExceptionInInitializerError(th);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Clean cash and close connection DB
        getSessionFactory().close();
    }
}
