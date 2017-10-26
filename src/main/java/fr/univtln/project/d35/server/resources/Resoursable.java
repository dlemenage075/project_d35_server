package fr.univtln.project.d35.server.resources;

import fr.univtln.project.d35.server.exception.ResourceException;
import javax.ws.rs.core.Response;

public interface Resoursable<T> {
    Response get(long var1) throws ResourceException;

    Response fetch();

    Response insert(T var1);

    Response merge(T var1, long var2) throws ResourceException;

    Response delete(long var1) throws ResourceException;
}
