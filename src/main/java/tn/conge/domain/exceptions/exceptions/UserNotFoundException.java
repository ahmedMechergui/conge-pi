package tn.conge.domain.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super(500, MessageSourceUtils.fetchMessage("exception.UserNotFound"), HttpStatus.NOT_FOUND);
    }
}
