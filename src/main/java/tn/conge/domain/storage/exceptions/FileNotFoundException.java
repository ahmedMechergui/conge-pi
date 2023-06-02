package tn.conge.domain.storage.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;


public class FileNotFoundException extends CustomException {
    public FileNotFoundException() {
        super(1903, MessageSourceUtils.fetchMessage("exception.FileNotFound"), HttpStatus.NOT_FOUND);
    }
}
