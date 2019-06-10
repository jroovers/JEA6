package domain.dao.impl.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import domain.dao.IBaseDao;

/**
 *
 * @author Jeroen Roovers
 */
public abstract class BaseDaoJpa<T> implements IBaseDao<T> {

    @PersistenceContext(unitName = "kwetterHibernate")
    private EntityManager em;

    protected Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void flush() {
        this.em.flush();
    }

    public void clearCache() {
        this.flush();
        this.em.clear();
    }

    @Override
    public List<T> getAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityClass()));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public T getById(Long id) {
        return em.find(getEntityClass(), id);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

}
