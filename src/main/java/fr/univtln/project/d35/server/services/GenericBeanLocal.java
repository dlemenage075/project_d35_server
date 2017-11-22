package fr.univtln.project.d35.server.services;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GenericBeanLocal<T> {
    T find(Class<T> tClass, long id);
    List<T> findAll(Class className);
    void persist(T t);
    T merge(T t);
    void remove(T t);
}
