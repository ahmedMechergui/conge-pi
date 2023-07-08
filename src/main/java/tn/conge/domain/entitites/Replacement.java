package tn.conge.domain.entitites;

import lombok.Getter;
import lombok.Setter;
import tn.conge.domain.enums.ReplacementStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
