package com.youcode.wrm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WaitingRoomFullException extends RuntimeException {
    public WaitingRoomFullException(String message) {
        super(message);
    }
}