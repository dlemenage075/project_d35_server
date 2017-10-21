package fr.univtln.project.d35.server.crud;

import fr.univtln.project.d35.server.exception.ResourceException;

public interface CrudService {
    <T> T create(T var1) throws ResourceException;

    <T> T find(Class<T> var1, Object var2) throws ResourceException;

    <T> T update(T var1) throws ResourceException;

    void delete(Class var1, Object var2) throws ResourceException;
}
