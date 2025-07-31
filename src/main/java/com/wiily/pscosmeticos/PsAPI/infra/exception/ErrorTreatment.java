package com.wiily.pscosmeticos.PsAPI.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
public class ErrorTreatment {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> notFoundError(EntityNotFoundException e) {
        String s = e.getMessage();
        System.out.println(s);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> argumentInvalid(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(InvalidArgument::new));

    }
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Object> missingRequestPart(MissingServletRequestPartException e) {
        String s = String.valueOf(e.getBody());
        return ResponseEntity.badRequest().body(e.getRequestPartName() + " " + s);
    }


    private record InvalidArgument(String camp, String desc) {
        public InvalidArgument(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
//    private record MissingRequestPartField(String camp, String idnt) {
//        public MissingRequestPartField()
//    }
}
