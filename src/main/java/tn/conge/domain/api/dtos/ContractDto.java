package tn.conge.domain.api.dtos;

import lombok.Data;
import tn.conge.domain.entitites.Contract;
import tn.conge.domain.enums.ContractStatus;
import tn.conge.domain.enums.ContractType;
import tn.conge.domain.storage.rest.CustomFileDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link Contract} entity
 */
@Data
public class ContractDto implements Serializable {
    private final Date createdAt;
    private final Date updatedAt;
    private final Long id;
    private final String contractName;
    private final EmployeeDto employee;
    private final EmployeeDto writtenBy;
    private final DatePeriodDto period;
    private final ContractStatus status;
    private final ContractType type;
    private final String notes;
    private final CustomFileDto content;
    private final CustomFileDto signature;
    private final List<CustomFileDto> relatedDocuments;
}