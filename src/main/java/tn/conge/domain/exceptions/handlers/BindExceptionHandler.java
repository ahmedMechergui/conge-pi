package tn.conge.domain.exceptions.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tn.conge.domain.exceptions.exceptions.CustomException;
import tn.conge.domain.exceptions.exceptions.ExceptionResponse;
import tn.conge.domain.exceptions.exceptions.FieldErrorException;
import tn.conge.domain.exceptions.exceptions.FieldErrorVM;


import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BindExceptionHandler extends CustomExceptionHandler<BindException> {
    @Override
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> handle(BindException exception) {
        CustomException customException = new FieldErrorException(this.fieldErrorsToFieldVMs(exception.getFieldErrors()));
        return super.forgeCustomExceptionResponse(customException);
    }

    private List<FieldErrorVM> fieldErrorsToFieldVMs(List<FieldError> fieldErrors) {
        List<FieldErrorVM> fieldErrorVMs = new ArrayList<>();
        fieldErrors.forEach(error -> {
            FieldErrorVM fieldErrorVM = new FieldErrorVM(error.getObjectName(), error.getField(), error.getDefaultMessage());
            fieldErrorVMs.add(fieldErrorVM);
        });
        return fieldErrorVMs;
    }
}
