package tn.conge.domain.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;

public class UniqueKeyAlreadyExistsException extends CustomException {

    public UniqueKeyAlreadyExistsException() {
        super(1503, MessageSourceUtils.fetchMessage("exception.UniqueKeyAlreadyExists"), HttpStatus.CONFLICT);
    }

    public UniqueKeyAlreadyExistsException(String message) {
        super(1503, message, HttpStatus.CONFLICT);
    }
}
