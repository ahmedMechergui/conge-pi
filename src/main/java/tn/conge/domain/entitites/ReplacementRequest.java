package tn.conge.domain.entitites;

import lombok.Getter;
import lombok.Setter;
import tn.conge.domain.embeddables.DatePeriod;
import tn.conge.domain.enums.ReplacementRequestStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class ReplacementRequest extends BaseEntity {

    @Column
    private String position;

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "skill_id"))
    private Set<String> skills;

    @Column
    private ReplacementRequestStatus status;

    @Embedded
    private DatePeriod period;

    @ManyToMany
    private List<Employee> potentialCandidates;

    @ManyToOne
    private Employee replacedEmployee;

    @OneToOne(cascade = CascadeType.ALL)
    private Replacement replacement;
}
