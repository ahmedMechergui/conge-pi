package tn.conge.domain.code.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;

public class CodeCannotBeNullException extends CustomException {
    public CodeCannotBeNullException() {
        super(1301, MessageSourceUtils.fetchMessage("exception.CodeCannotBeNull"), HttpStatus.BAD_REQUEST);
    }
}
