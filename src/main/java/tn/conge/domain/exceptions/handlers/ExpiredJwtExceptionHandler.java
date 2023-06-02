package tn.conge.domain.exceptions.handlers;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tn.conge.domain.exceptions.exceptions.security.ExpiredTokenException;
import tn.conge.domain.exceptions.exceptions.ExceptionResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExpiredJwtExceptionHandler extends CustomExceptionHandler<ExpiredJwtException> {

    @Override
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(ExpiredJwtException exception) {
        return super.forgeCustomExceptionResponse(new ExpiredTokenException());
    }
}
