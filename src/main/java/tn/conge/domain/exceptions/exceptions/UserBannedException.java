package tn.conge.domain.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;


public class UserBannedException extends CustomException {

    public UserBannedException() {
        super(1104, MessageSourceUtils.fetchMessage("exception.UserBanned"), HttpStatus.FORBIDDEN);
    }
}
