package tn.conge.domain.storage.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;

public class InvalidFileTypeException extends CustomException {
    public InvalidFileTypeException() {
        super(1902, MessageSourceUtils.fetchMessage("exception.InvalidFileType"), HttpStatus.BAD_REQUEST);
    }
}
