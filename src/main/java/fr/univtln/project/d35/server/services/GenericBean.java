package fr.univtln.project.d35.server.services;

import lombok.extern.java.Log;

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

@Stateless
@Log
public class GenericBean<T>{

    @PersistenceContext(unitName = "payara_postgres")
    EntityManager em;

    /**
     * Find all T class thank to his class name
     * @param className Use to make dynamic query
     * @return
     */
    public List<T> findAll(Class className) {

        // Create dynamically the query "SELECT * FROM T t"
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

    public T persist(T t) {
        validate(t);
        this.em.persist(t);
        return t ;
    }

    public T merge(T t) {
        validate(t);
        return this.em.merge(t);
    }

    public void remove(T t) {
        this.em.remove(t);
    }

    public void validate(T t){

        // Validator make sure the T class is correct before persisting it in DB
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> constraintViolations =
                validator.validate( t );

        // Show all violations of T class in log
        for (ConstraintViolation<T> violation : constraintViolations) {
            log.warning(violation.getMessage());
        }

    }

}

