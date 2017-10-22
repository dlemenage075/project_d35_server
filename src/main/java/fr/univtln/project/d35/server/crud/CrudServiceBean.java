package fr.univtln.project.d35.server.crud;


import fr.univtln.project.d35.server.exception.ResourceException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CrudServiceBean {
    EntityManagerFactory entityManagerFactory;
    EntityManager em;
    EntityTransaction transaction;
    public static final String PU_H2 = "h2";
    public static final String PU_DOCKER_POSTGRES = "postgres";

    public CrudServiceBean() {
        this.setPersistenceUnit(PU_DOCKER_POSTGRES);
    }

    public CrudServiceBean(String persistence_unit) {
        this.setPersistenceUnit(persistence_unit);
    }

    public void setPersistenceUnit(String persistence_unit) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistence_unit);
        this.em = this.entityManagerFactory.createEntityManager();
        this.transaction = this.em.getTransaction();
    }

    public Query createQuery(String string) {
        this.em.createNamedQuery(string);
        return null;
    }

    public void newTransaction() {
        if (!this.transaction.isActive()) {
            this.transaction.begin();
        }

    }

    public void closeTransaction() {
        this.em.close();
        this.entityManagerFactory.close();
    }

    public void clearCache() {
        this.em.clear();
    }

    public void commit() {
        this.transaction.commit();
    }

    public <T> T create(T t) throws ResourceException {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    public <T> T find(Class<T> type, Object id) throws ResourceException {
        T data = this.em.find(type, id);
        return data;
    }

    public void delete(Class type, Object id) throws ResourceException {
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
    }

    public <T> T update(T t) throws ResourceException {
        T data = this.em.merge(t);
        return data;
    }

    public <T> List findWithNamedQuery(Class<T> type, String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    public <T> List findWithNamedQuery(Class<T> type, String namedQueryName, Map parameters) {
        return this.findWithNamedQuery(type, namedQueryName, parameters, 0);
    }

    public <T> List findWithNamedQuery(Class<T> type, String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).setMaxResults(resultLimit).getResultList();
    }

    public List findByNativeQuery(String sql, Class type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    public <T> List findWithNamedQuery(Class<T> type, String namedQueryName, Map parameters, int resultLimit) {
        Set rawParameters = parameters.keySet();
        TypedQuery<T> query = this.em.createNamedQuery(namedQueryName, type);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }

        Iterator var7 = rawParameters.iterator();

        while(var7.hasNext()) {
            Object entry = var7.next();
            query.setParameter(entry.toString(), parameters.get(entry.toString()));
        }

        return query.getResultList();
    }

    public <T> List findAll(Class<T> clazz) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> rootEntry = cq.from(clazz);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = this.em.createQuery(all);
        return allQuery.getResultList();
    }

    public boolean exist(Class type, Object id) throws ResourceException {
        return this.find(type, id) != null;
    }

    public EntityManager getEm() {
        return this.em;
    }
}

