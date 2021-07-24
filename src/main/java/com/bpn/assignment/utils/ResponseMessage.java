package com.bpn.assignment.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessage<T> {
    private T data;
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseEntity success() {
        return ResponseEntity.ok(new ResponseMessage("Successful"));
    }

    public static ResponseEntity success(String message) {
        return ResponseEntity.ok(new ResponseMessage(message));
    }

    public static ResponseEntity success(Object object) {
        return ResponseEntity.ok(new ResponseMessage(object,"Successful"));
    }

    public static ResponseEntity success(Object object, String message) {
        return ResponseEntity.ok(new ResponseMessage(object, message));
    }

    public static ResponseEntity generate(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ResponseMessage(message));
    }

    public static ResponseEntity generate(Object object, String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ResponseMessage(object, message));
    }
}
