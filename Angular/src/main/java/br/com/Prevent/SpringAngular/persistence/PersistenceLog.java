package br.com.Prevent.SpringAngular.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.Prevent.SpringAngular.dominio.Log;


@Component
public class PersistenceLog {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
	EntityManager manager = emf.createEntityManager();

	public Log salvar(Log log) {

		try {

			manager.getTransaction().begin();
			manager.persist(log);
			manager.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();

		}
		 return log;
	}

	public List<Log> listAll() {

		String jpql = "select l from Log l";

		Query query = manager.createQuery(jpql, Log.class);

		return query.getResultList();

	}

	public Log findByid(Long id) {

		return manager.find(Log.class, id);

	}

	public void update(Log log) {
		try {
			manager.getTransaction().begin();
			manager.merge(log);
			manager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();

		}

	}

	public List<Log> findByName(String metodo) {

		String jpql = "select l from Log l where l.metodo = :metodo";

		Query query = manager.createQuery(jpql);
		query.setParameter("metodo", metodo);
		return query.getResultList();

	}

}
