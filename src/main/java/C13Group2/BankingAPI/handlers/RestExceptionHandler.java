package C13Group2.BankingAPI.handlers;

import C13Group2.BankingAPI.exceptions.ResourceNotFoundException;
import C13Group2.BankingAPI.response.ErrorDetail;
import C13Group2.BankingAPI.response.ErrorResponse;
import C13Group2.BankingAPI.response.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleDepositException(ResourceNotFoundException resourceNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(resourceNotFoundException.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Validation Failed");
        errorDetail.setDetail(methodArgumentNotValidException.getMessage());
        errorDetail.setDeveloperMessage(methodArgumentNotValidException.getClass().getName());

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            List<ValidationError> validationErrors = errorDetail.getErrors().get(fieldError.getField());
            if (validationErrors == null) {
                validationErrors = new ArrayList<>();
                errorDetail.getErrors().put(fieldError.getField(), validationErrors);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fieldError.getCode());
            validationError.setMessage(this.messageSource.getMessage(fieldError, null));
            validationErrors.add(validationError);
        }

        return (super.handleExceptionInternal(methodArgumentNotValidException, errorDetail, headers, status, request));
    }
@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException httpMessageNotReadableException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail(httpMessageNotReadableException.getMessage());
        errorDetail.setDeveloperMessage(httpMessageNotReadableException.getClass().getName());

        return (super.handleExceptionInternal(httpMessageNotReadableException, errorDetail, headers, status, request));
    }



}
