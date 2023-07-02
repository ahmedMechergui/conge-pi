package tn.conge.domain.api.dtos;

import lombok.Data;
import tn.conge.domain.entitites.Conge;
import tn.conge.domain.enums.TypeConge;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * A DTO for the {@link Conge} entity
 */
@Data
public class CongeDto implements Serializable {
    private final Date createdAt;
    private final Date updatedAt;
    private final Long id;
    private final String employe;
    private final LocalDate dateDebut;
    private final LocalDate dateFin;
    private final String notification;
    private final Boolean decision;
    private final Long nbrjours;
    private final TypeConge typeConge;
    private final EmployeeDto employeeDto;
}