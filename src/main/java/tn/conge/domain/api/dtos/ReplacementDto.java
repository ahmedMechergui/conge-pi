package tn.conge.domain.api.dtos;

import lombok.Data;
import tn.conge.domain.entitites.Replacement;
import tn.conge.domain.enums.ReplacementStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Replacement} entity
 */
@Data
public class ReplacementDto implements Serializable {
    private final Date createdAt;
    private final Date updatedAt;
    private final Long id;
    private final EmployeeDto replacedBy;
    private final ReplacementStatus status;
}