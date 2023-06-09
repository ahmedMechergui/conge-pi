package tn.conge.domain.exceptions.handlers;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tn.conge.domain.exceptions.exceptions.ExceptionResponse;
import tn.conge.domain.exceptions.exceptions.security.InvalidTokenException;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SignatureExceptionHandler extends CustomExceptionHandler<SignatureException> {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(SignatureException exception) {
        return this.forgeCustomExceptionResponse(new InvalidTokenException());
    }
}
