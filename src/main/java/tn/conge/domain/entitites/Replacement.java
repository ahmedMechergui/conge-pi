package tn.conge.domain.entitites;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tn.conge.domain.embeddables.DatePeriod;
import tn.conge.domain.enums.ReplacementStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Replacement extends BaseEntity {

    @ManyToOne
    private Employee replacedBy;

    @Column
    private ReplacementStatus status;

    @OneToOne
    private ReplacementRequest replacementRequest;

}
