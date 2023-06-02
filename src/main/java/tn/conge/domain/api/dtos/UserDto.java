package tn.conge.domain.api.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import tn.conge.core.security.UserRole;
import tn.conge.domain.entitites.User;
import tn.conge.domain.storage.rest.CustomFileDto;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Getter
@Setter
@FieldNameConstants
public class UserDto implements Serializable {
    private Long id;
    private String phone;
    private UserRole role = UserRole.ROLE_USER;
    private String firstName;
    private String lastName;
    private CustomFileDto photo;
}