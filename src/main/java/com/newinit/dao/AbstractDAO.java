package com.newinit.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ceasar
 * @param <T>
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AbstractDAO<T extends Serializable> {

    @PersistenceContext(unitName = "Template-UP")
    protected EntityManager em;

    private final Class<T> clazz;

    public AbstractDAO(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> findAll() {
        Query query = em.createQuery(String.format("FROM %s", clazz.getName()), clazz);
        return query.getResultList();
    }

    public T findId(int id) {
        return em.find(clazz, id);
    }

    public void merge(T entity) {
        em.merge(entity);
    }

    public void persist(T entity) {
        em.persist(entity);
    }

    public void remove(T entity) {
        em.remove(entity);
    }
}
