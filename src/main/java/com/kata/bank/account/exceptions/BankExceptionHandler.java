package com.kata.bank.account.exceptions;

import com.kata.bank.account.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Bank exception handler.
 */
@ControllerAdvice
public class BankExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle invalid amount exception .
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = InvalidAmountException.class)
    public ResponseEntity<ErrorMessage> handleInvalidAmountException(InvalidAmountException exception) {

        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle account not found exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleAccountNotFoundException(AccountNotFoundException exception) {

        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
