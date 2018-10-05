package com.ky.blog.core.model;

public class Response<T> {

    private boolean success;

    private String message;

    private T body;

    protected Response(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    protected Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public static <T> Response ok(String message, T body){
        return new Response(true, message, body);
    }

    public static <T> Response fail(String message){
        return new Response(false, message);
    }
}
