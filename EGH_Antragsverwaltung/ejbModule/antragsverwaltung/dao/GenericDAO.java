package antragsverwaltung.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<B> {

	private final String UNIT_NAME = "Form_EJB";

	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;

	private Class<B> entityClass;

	public GenericDAO() {
	}

	public GenericDAO(Class<B> entityClass) {
		this.entityClass = entityClass;
	}

	public void save(B entity) {
		System.out.println("dao save");
		this.em.persist(entity);
	}

	public B update(B entity) {

		return em.merge(entity);
	}

	protected boolean delete(Object id, Class<B> classe) {
		B entityToBeRemoved = em.getReference(classe, id);
		try {
			em.remove(entityToBeRemoved);
			return true;
		} catch (Exception e) {
			System.out.println("Fehler beim Speichern der Id: " + id.toString());
			return false;
		}
	}

	public B find(int entityId) {
		return em.find(entityClass, entityId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<B> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected B findOneResult(String namedQuery, Map<String, Object> parameters) {
		B result = null;
		try {
			Query query = em.createNamedQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (B) query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Fehler bei der Query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected List<B> findListResult(String namedQuery, Map<String, Object> parameters) {
		List<B> result = null;
		try {
			Query query = em.createNamedQuery(namedQuery);
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}
			result = (List<B>) query.getResultList();
		} catch (Exception e) {
			System.out.println("Fehler bei der Query: " + e.getMessage());
		}
		return result;
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

}
