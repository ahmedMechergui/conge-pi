package tn.conge.domain.entitites;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import tn.conge.domain.entitites.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@FieldNameConstants
public class Reclamation extends BaseEntity {
    private String reclamationType;
    private String reclamationContent;
    @Temporal( TemporalType.DATE )
    private Date reclamationDate;
}
