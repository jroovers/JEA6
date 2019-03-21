package persistence.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.IBaseDao;

/**
 *
 * @author Jeroen Roovers
 */
public abstract class BaseDaoJpa<T> implements IBaseDao<T> {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    private Class<T> entityClass;

    @Override
    public List<T> getAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public T getById(Long id) {
        return em.find(entityClass, id);
    }

}
