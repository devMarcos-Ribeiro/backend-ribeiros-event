package com.marcosgrs.ribeirosevents.exceptionHandler;

import com.marcosgrs.ribeirosevents.controllers.dto.ErrorResponse;
import com.marcosgrs.ribeirosevents.controllers.dto.errors.ValidationErrorResponse;
import com.marcosgrs.ribeirosevents.exceptions.BadRequestException;
import com.marcosgrs.ribeirosevents.exceptions.NotFoundException;
import com.marcosgrs.ribeirosevents.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RibeirosEventsExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public RibeirosEventsExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
         HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrorResponse> errors = errorsList(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<ValidationErrorResponse> errorsList(BindingResult bindingResult){
        List<ValidationErrorResponse> errors = new ArrayList<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            errors.add(new ValidationErrorResponse(fieldError.getField(), message));
        }
        return errors;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex,
                                                            WebRequest request) {
        return handleExceptionInternal(
                ex, buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST)
                , new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex,
                                                            WebRequest request) {
        return handleExceptionInternal(
                ex, buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND)
                , new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleNotFoundException(UnauthorizedException ex,
                                                          WebRequest request) {
        return handleExceptionInternal(
                ex, buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    private ErrorResponse buildErrorResponse(String message, HttpStatus status) {
        return ErrorResponse.builder()
                .message(message)
                .status(status)
                .timestamp(OffsetDateTime.now())
                .build();
    }
}
