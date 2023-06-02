package tn.conge.domain.exceptions.exceptions.security;

import org.springframework.http.HttpStatus;
import tn.conge.domain.exceptions.exceptions.CustomException;

public final class AccessDeniedCustomException extends CustomException {

    public AccessDeniedCustomException() {
        super(1101, "Access denied", HttpStatus.UNAUTHORIZED);
    }
}
