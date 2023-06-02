package tn.conge.domain.code.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;

public class InvalidKeyException extends CustomException {
    public InvalidKeyException() {
        super(1303, MessageSourceUtils.fetchMessage("exception.InvalidKey"), HttpStatus.BAD_REQUEST);
    }
}

