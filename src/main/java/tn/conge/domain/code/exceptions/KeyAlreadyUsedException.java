package tn.conge.domain.code.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;

public class KeyAlreadyUsedException extends CustomException {
    public KeyAlreadyUsedException() {
        super(1304, MessageSourceUtils.fetchMessage("exception.KeyAlreadyUsed"), HttpStatus.BAD_REQUEST);
    }
}
