package tn.conge.domain.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import tn.conge.domain.entitites.BaseEntity;

public class EntityNotFoundException extends CustomException {

    public EntityNotFoundException(Class<? extends BaseEntity> clazz) {
        super(4004, "Entity " + clazz.getSimpleName() + " not found", HttpStatus.NOT_FOUND);
    }
}
