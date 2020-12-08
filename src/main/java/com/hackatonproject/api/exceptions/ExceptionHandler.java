package com.hackatonproject.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    //member not found
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MemberNotFoundException exc){

        ErrorResponse response =
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND.value(),
                            exc.getMessage(),
                            System.currentTimeMillis()
                    );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //entity already exists in db
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(DuplicateEntryException exc){
        System.out.println(exc.getMessage());
        ErrorResponse response =
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        exc.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Member skills duplicates in array
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MemberSkillsDuplicateException exc){
        System.out.println(exc.getMessage());
        ErrorResponse response =
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        exc.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Main skill is not in member skill
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(SkillNotInMemberSkillsException exc){
        System.out.println(exc.getMessage());
        ErrorResponse response =
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        exc.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Heist not found exception
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(HeistNotFoundException exc){
        System.out.println(exc.getMessage());
        ErrorResponse response =
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        exc.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Duplicate required skills
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(DuplicateRequriedSkillException exc){
        System.out.println(exc.getMessage());
        ErrorResponse response =
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        exc.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Duplicate required skills
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(SkillNotFoundException exc){
        System.out.println(exc.getMessage());
        ErrorResponse response =
                new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        exc.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //all
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc){

        ErrorResponse response =
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        exc.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
