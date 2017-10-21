package fr.univtln.project.d35.server.response;

import javax.ws.rs.core.Response;
import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestResponse<T> implements Serializable {
    private int httpErrorCode;
    private T data;
    private String errorMessage;
    public static final String MEDIA_TYPE = "application/json;charset=utf-8";

    public RestResponse() {
        this.httpErrorCode = 0;
        this.data = null;
        this.errorMessage = null;
    }

    public RestResponse(int httpErrorCode, T data, String errorMessage) {
        this.httpErrorCode = httpErrorCode;
        this.data = data;
        System.out.println(this.data);
        this.errorMessage = errorMessage;
    }

    public Response throw200Ok() {
        Response.Status status = Response.Status.OK;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(asJsonString(this)).type("application/json;charset=utf-8").build();
    }

    public Response throw201Created() {
        Response.Status status = Response.Status.CREATED;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(this).type("application/json;charset=utf-8").build();
    }

    public Response throw204NoContent() {
        Response.Status status = Response.Status.NO_CONTENT;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(this).type("application/json;charset=utf-8").build();
    }

    public Response throw400BadRequest() {
        Response.Status status = Response.Status.BAD_REQUEST;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(this).type("application/json;charset=utf-8").build();
    }

    public Response throw403Forbidden() {
        Response.Status status = Response.Status.FORBIDDEN;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(this).type("application/json;charset=utf-8").build();
    }

    public Response throw404NotFound() {
        Response.Status status = Response.Status.NOT_FOUND;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(this).type("application/json;charset=utf-8").build();
    }

    /*public Response throw405MethodNotAllowed() {
        Response.Status status = Response.Status.METHOD_NOT_ALLOWED;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(this).type("application/json;charset=utf-8").build();
    }*/

    public Response throw409Conflict() {
        Response.Status status = Response.Status.CONFLICT;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(this).type("application/json;charset=utf-8").build();
    }

    public Response throw500InternalServerError() {
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        this.setHttpErrorCode(status.getStatusCode());
        return Response.status(status).entity(this).type("application/json;charset=utf-8").build();
    }

    public int getHttpErrorCode() {
        return this.httpErrorCode;
    }

    private void setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public boolean isSuccess() {
        return this.httpErrorCode == 200;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static String asJsonString(Object obj) {
        try {
            return (new ObjectMapper()).writeValueAsString(obj);
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }
}
