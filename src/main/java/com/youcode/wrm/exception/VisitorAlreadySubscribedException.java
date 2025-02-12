package com.youcode.wrm.exception;

public class VisitorAlreadySubscribedException extends RuntimeException {
    public VisitorAlreadySubscribedException() {
        super("This user is already subscribed to the waiting list");
    }
}

