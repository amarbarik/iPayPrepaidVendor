package za.co.ipay.prepaid.vendor.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataBaseUtil {

    private static final SessionFactory sessionFactory;


    static {
        try {
            System.out.println("------------Begin SessionFactory creation------------------");
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed. [{}]" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}