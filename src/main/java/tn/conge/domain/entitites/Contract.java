package tn.conge.domain.entitites;

import lombok.Getter;
import lombok.Setter;
import tn.conge.domain.embeddables.DatePeriod;
import tn.conge.domain.enums.ContractStatus;
import tn.conge.domain.enums.ContractType;
import tn.conge.domain.storage.CustomFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Contract extends BaseEntity {

    @Column(nullable = false)
    private String contractName;

    @OneToOne()
    private Employee employee;

    @ManyToOne()
    private Employee writtenBy;

    @Enumerated(EnumType.STRING)
    @Column
    private ContractStatus status;

    @Enumerated(EnumType.STRING)
    @Column
    private ContractType type;


    @Lob
    private String notes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private CustomFile content; // Usually a PDF

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "contract_document",
            joinColumns = {@JoinColumn(name = "contract_id")},
            inverseJoinColumns = {@JoinColumn(name = "document_id")}
    )
    private List<CustomFile> relatedDocuments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private CustomFile signature; // Electronic signature
    @Embedded
    private DatePeriod period;
}
