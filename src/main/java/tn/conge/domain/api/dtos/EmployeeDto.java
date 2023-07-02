package tn.conge.domain.api.dtos;

import lombok.Data;
import tn.conge.domain.entitites.Employee;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link Employee} entity
 */
@Data
public class EmployeeDto implements Serializable {
    private final Date createdAt;
    private final Date updatedAt;
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<CongeDto> conges;
    private final ContractDto contract;
}