package tn.conge.domain.exceptions.exceptions.security;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;

public final class ExpiredTokenException extends CustomException {
    public ExpiredTokenException() {
        super(1102, MessageSourceUtils.fetchMessage("exception.ExpiredToken"), HttpStatus.UNAUTHORIZED);
    }
}
