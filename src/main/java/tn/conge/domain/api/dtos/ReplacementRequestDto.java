package tn.conge.domain.api.dtos;

import lombok.Data;
import tn.conge.domain.entitites.ReplacementRequest;
import tn.conge.domain.enums.ReplacementRequestStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * A DTO for the {@link ReplacementRequest} entity
 */
@Data
public class ReplacementRequestDto implements Serializable {
    private final Date createdAt;
    private final Date updatedAt;
    private final Long id;
    private final String position;
    private final Set<String> skills;
    private final ReplacementRequestStatus status;
    private final DatePeriodDto period;
    private final List<EmployeeDto> potentialCandidates;
    private final EmployeeDto replacedEmployee;
    private final ReplacementDto replacement;
}