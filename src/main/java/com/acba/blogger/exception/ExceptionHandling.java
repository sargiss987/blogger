package com.acba.blogger.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

  @ExceptionHandler(UserEmailAlreadyExistException.class)
  public ResponseEntity<Map<String, String>> userEmailAlreadyExistException(
      UserEmailAlreadyExistException exception) {
    Map<String, String> exceptionResponse = new HashMap<>();
    exceptionResponse.put("message", exception.getMessage());
    return ResponseEntity.badRequest().body(exceptionResponse);
  }
}
