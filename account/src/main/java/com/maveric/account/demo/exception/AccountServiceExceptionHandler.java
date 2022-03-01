package com.maveric.account.demo.exception;
import com.maveric.account.demo.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice
public class AccountServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({AccountNotFoundException.class})
    ResponseEntity<ApiError> accountNotFoundHandler(Exception exception, ServletWebRequest request)
    {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setErrors(Arrays.asList(exception.getMessage()));
        apiError.setPath(request.getDescription(false));
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({CustomerNotActiveException.class})
    ResponseEntity<ApiError> customerNotActiveHandler(Exception exception, ServletWebRequest request)
    {
        ApiError apiErrorCustomerNotActive = new ApiError();
        apiErrorCustomerNotActive.setStatus(HttpStatus.NOT_FOUND);
        apiErrorCustomerNotActive.setErrors(Arrays.asList(exception.getMessage()));
        apiErrorCustomerNotActive.setPath(request.getDescription(false));
        apiErrorCustomerNotActive.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiErrorCustomerNotActive, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({CustomerNotFoundException.class})
    ResponseEntity<ApiError> customerNotFoundHandler(Exception exception, ServletWebRequest request)
    {
        ApiError apiErrorCustomerNotFound = new ApiError();
        apiErrorCustomerNotFound.setStatus(HttpStatus.NOT_FOUND);
        apiErrorCustomerNotFound.setErrors(Arrays.asList(exception.getMessage()));
        apiErrorCustomerNotFound.setPath(request.getDescription(false));
        apiErrorCustomerNotFound.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiErrorCustomerNotFound, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({AccountTypeAlreadyExistsException.class})
    ResponseEntity<ApiError>accountTypeAlreadyExistsHandler(Exception exception, ServletWebRequest request)
    {
        ApiError apiErrorAccountTypeExists = new ApiError();
        apiErrorAccountTypeExists.setStatus(HttpStatus.NOT_FOUND);
        apiErrorAccountTypeExists.setErrors(Arrays.asList(exception.getMessage()));
        apiErrorAccountTypeExists.setPath(request.getDescription(false));
        apiErrorAccountTypeExists.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiErrorAccountTypeExists, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = fieldErrors.stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .collect(Collectors.toList());


        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setPath(request.getDescription(false));
        apiError.setErrors(errors);

        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }
}
