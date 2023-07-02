package tn.conge.domain.entitites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.conge.domain.enums.TypeConge;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Conge extends BaseEntity{

    private String employe;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String notification;
    private Boolean decision;
    private Long nbrjours;
    @Enumerated(EnumType.STRING)
    private TypeConge typeConge;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
