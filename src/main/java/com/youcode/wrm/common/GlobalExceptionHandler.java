package com.youcode.wrm.common;

import com.youcode.wrm.exception.NoVisitorsWaitingException;
import com.youcode.wrm.exception.VisitorAlreadySubscribedException;
import com.youcode.wrm.exception.WaitingRoomDatePassedException;
import com.youcode.wrm.exception.WaitingRoomFullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WaitingRoomDatePassedException.class)
    public ResponseEntity<String> handleWaitingListDatePassedException(WaitingRoomDatePassedException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoVisitorsWaitingException.class)
    public ResponseEntity<String> handleNoVisitorsWaitingException(NoVisitorsWaitingException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VisitorAlreadySubscribedException.class)
    public ResponseEntity<String> handleUserAlreadySubscribedException(VisitorAlreadySubscribedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {

        System.err.println("Error: " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    }


