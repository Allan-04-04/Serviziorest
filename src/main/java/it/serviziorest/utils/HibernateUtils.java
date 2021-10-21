package it.serviziorest.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

	/**
	 * Create a sessionFactory object with the parameters specified on
	 * hibernate.cfg.xml
	 * 
	 * @return SessionFactory object
	 */
	private static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory = null;

		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
		return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
