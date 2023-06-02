package tn.conge.domain.storage.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.exceptions.exceptions.CustomException;

public class FileUploadException extends CustomException {
    public FileUploadException() {
        super(1901, MessageSourceUtils.fetchMessage("exception.FileUpload"), HttpStatus.BAD_REQUEST);
    }
}
