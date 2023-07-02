package tn.conge.domain.entitites;

import lombok.Getter;
import lombok.Setter;
import tn.conge.domain.enums.ContractStatus;
import tn.conge.domain.enums.ContractType;
import tn.conge.domain.storage.CustomFile;

import javax.persistence.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Contract extends BaseEntity {

    @Column(nullable = false)
    private String contractName;

    @OneToOne
    private Employee employee;

    @ManyToOne
    private Employee writtenBy;

    @Temporal(TemporalType.DATE)
    @Column
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column
    private ContractStatus status;

    @Enumerated(EnumType.STRING)
    @Column
    private ContractType type;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "notes", joinColumns = @JoinColumn(name = "note_id"))
    private List<String> notes;

    @OneToOne
    @JoinColumn
    private CustomFile content; // Usually a PDF

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "contract_document",
            joinColumns = {@JoinColumn(name = "contract_id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id")}
    )
    private List<CustomFile> relatedDocuments = new ArrayList<>();

    @Lob
    @Column
    private String signature; // Electronic signature
    @Column
    private Period trialPeriod;
}
