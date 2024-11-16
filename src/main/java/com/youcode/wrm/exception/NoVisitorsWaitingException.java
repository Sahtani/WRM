package com.youcode.wrm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoVisitorsWaitingException extends RuntimeException {
    public NoVisitorsWaitingException() {
        super("No visitors waiting in the queue");
    }

}