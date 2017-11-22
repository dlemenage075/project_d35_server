package fr.univtln.project.d35.server.services;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class GenericBean<T> implements GenericBeanLocal<T>,GenericBeanRemote<T>{

    final Logger LOG = Logger.getLogger(GenericBean.class.getName());

    @PersistenceContext(unitName = "payara_postgres")
    EntityManager em;

    public List<T> findAll(Class className) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(className);
        Root<T> rootEntry = cq.from(className);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);

        return allQuery.getResultList();
    }

    public T find(Class<T> tClass, long id) {
        return this.em.find(tClass, id);
    }

    public void persist(T t) {

        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> constraintViolations =
                validator.validate( t );

        for (ConstraintViolation<T> violation : constraintViolations) {
            LOG.warning(violation.getMessage());
        }

        this.em.persist(t);
    }

    public T merge(T t) {

        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> constraintViolations =
                validator.validate( t );

        for (ConstraintViolation<T> violation : constraintViolations) {
            LOG.warning(violation.getMessage());
        }

        return this.em.merge(t);
    }

    public void remove(T t) {
        this.em.remove(t);
    }

}

