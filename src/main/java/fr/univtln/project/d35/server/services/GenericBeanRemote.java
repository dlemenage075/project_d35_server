package fr.univtln.project.d35.server.services;


import javax.ejb.Remote;
import java.util.List;

@Remote
public interface GenericBeanRemote<T>{
    T find(Class<T> tClass, long id);
    List<T> findAll(Class className);
    void persist(T t);
    T merge(T t);
    void remove(T t);

}
