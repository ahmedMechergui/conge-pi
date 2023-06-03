package tn.conge.domain.entitites;

import tn.conge.domain.enums.ContractType;
import tn.conge.domain.storage.CustomFile;

import javax.persistence.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Contract extends BaseEntity {

    @Column(nullable = false)
    private String contractName;

    @ManyToOne
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
    private ContractType status; // ContractStatus is an enum type with values such as 'ACTIVE', 'EXPIRED', etc.

    @Enumerated(EnumType.STRING)
    @Column
    private ContractType type; // ContractType is an enum with values such as 'FULL_TIME', 'PART_TIME', etc.


    @Lob
    @Column
    private String notes;

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
