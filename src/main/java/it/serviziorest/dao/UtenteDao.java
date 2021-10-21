package it.serviziorest.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import it.serviziorest.entity.Utente;
import it.serviziorest.utils.HibernateUtils;

public class UtenteDao {
	private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	private Session session;
	private Transaction tx;

	private Integer id;

	public List<Utente> getAll() {
		List<Utente> utente = null;
		session = sessionFactory.openSession();
		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Utente> cr = cb.createQuery(Utente.class);
			Root<Utente> root = cr.from(Utente.class);
			cr.select(root);

			Query<Utente> query = session.createQuery(cr);
			utente = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return utente;
	}

	public Utente insert(Utente utente) {
		session = sessionFactory.openSession();
		org.hibernate.Transaction tx = null;
		id = null;
		try {

			tx = session.beginTransaction();
			id = (Integer) session.save(utente);
			session.flush();
			session.clear();
			utente = (Utente) session.get(Utente.class, utente.getId());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			utente = null;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return utente;
	}

	public Utente update(Utente utente) {
		session = sessionFactory.openSession();
		tx = null;
		try {

			tx = session.beginTransaction();
			session.update(utente);
			session.flush();
			session.clear();
			utente = (Utente) session.get(Utente.class, utente.getId());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			utente = null;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return utente;

	}

	public Utente delete(int id) {
		Utente utente = null;
		session = sessionFactory.openSession();
		tx = null;
		try {
			tx = session.beginTransaction();
			utente = (Utente) session.get(Utente.class, id);
			if (utente != null)
				session.delete(utente);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			utente = null;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return utente;
	}

	public Utente get(int id) {
		Utente utente = null;
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			utente = (Utente) session.get(Utente.class, id);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			utente = null;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return utente;
	}

}
