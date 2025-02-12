package com.youcode.wrm.exception;

public class WaitingRoomDatePassedException extends RuntimeException {
    public WaitingRoomDatePassedException(String message) {
        super(message);
    }

    public WaitingRoomDatePassedException(String message, Throwable cause) {
        super(message, cause);
    }
}
