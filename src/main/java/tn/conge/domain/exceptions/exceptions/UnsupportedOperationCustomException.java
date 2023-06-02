package tn.conge.domain.exceptions.exceptions;

import org.springframework.http.HttpStatus;


public class UnsupportedOperationCustomException extends CustomException {

    public UnsupportedOperationCustomException() {
        super(1505, "Unsupported Operation", HttpStatus.BAD_REQUEST);
    }
}
