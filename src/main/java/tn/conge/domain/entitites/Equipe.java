package tn.conge.domain.entitites;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import tn.conge.domain.enums.EquipeSpeciality;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@FieldNameConstants
public class Equipe extends BaseEntity{
    @Column
    private Integer equipeNumber;
    private String Name;
    @Enumerated(EnumType.STRING)
    private EquipeSpeciality equipeSpeciality;
    @OneToMany(mappedBy = "equipe")
    private List<User> users;
}
