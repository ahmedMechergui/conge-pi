package tn.conge.domain.code.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;

public class CodeExpiredException extends CustomException {
    public CodeExpiredException() {
        super(1302, MessageSourceUtils.fetchMessage("exception.CodeExpired"), HttpStatus.BAD_REQUEST);
    }
}
