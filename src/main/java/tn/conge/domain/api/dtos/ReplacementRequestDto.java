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
    private Date createdAt;
    private Date updatedAt;
    private Long id;
    private String position;
    private Set<String> skills;
    private ReplacementRequestStatus status = ReplacementRequestStatus.PENDING;
    private DatePeriodDto period;
    private List<EmployeeDto> potentialCandidates;
    private EmployeeDto replacedEmployee;
    private ReplacementDto replacement;
}