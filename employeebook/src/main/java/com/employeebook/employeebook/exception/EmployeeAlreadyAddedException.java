package com.employeebook.employeebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class EmployeeAlreadyAddedException extends RuntimeException{
}
