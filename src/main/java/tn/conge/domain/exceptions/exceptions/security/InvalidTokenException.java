package tn.conge.domain.exceptions.exceptions.security;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;

public final class InvalidTokenException extends CustomException {
    public InvalidTokenException() {
        super(1103, MessageSourceUtils.fetchMessage("exception.InvalidToken"), HttpStatus.UNAUTHORIZED);
    }
}
