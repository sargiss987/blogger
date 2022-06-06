package com.acba.blogger.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

  private final MessageSource messageSource;

  public ExceptionHandling(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(UserEmailAlreadyExistException.class)
  public ResponseEntity<Map<String, String>> userEmailAlreadyExistException(
      UserEmailAlreadyExistException exception) {
    Map<String, String> exceptionResponse = new HashMap<>();
    exceptionResponse.put("message", exception.getMessage());
    return ResponseEntity.badRequest().body(exceptionResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Map<String, String>>> validationFailedException(
      MethodArgumentNotValidException exception) {
    Map<String, String> validationErrors = getValidationErrors(exception);
    Map<String, Map<String, String>> validationErrorsResponse = new HashMap<>();
    validationErrorsResponse.put("validation errors", validationErrors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorsResponse);
  }

  private Map<String, String> getValidationErrors(MethodArgumentNotValidException exception) {
    return exception.getBindingResult().getAllErrors().stream()
        .collect(
            Collectors.toMap(
                error -> ((FieldError) error).getField(),
                objectError -> messageSource.getMessage(objectError, Locale.getDefault())));
  }
}
