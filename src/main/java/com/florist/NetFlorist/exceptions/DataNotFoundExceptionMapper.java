/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.florist.NetFlorist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/**
 *
 * @author User
 */
@ControllerAdvice
public class DataNotFoundExceptionMapper{
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorMessage> dataNotFound(DataNotFoundException dd)
    {
        ErrorMessage err = new ErrorMessage();
        //err.setErrorCode(404);
        err.setMessage(dd.getMessage());
        return new ResponseEntity<ErrorMessage>(err, HttpStatus.NOT_FOUND);
    }
}
