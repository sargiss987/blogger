package com.acba.blogger.exception;

public class UserEmailAlreadyExistException extends RuntimeException {
  public UserEmailAlreadyExistException(String message) {
    super(message);
  }
}
