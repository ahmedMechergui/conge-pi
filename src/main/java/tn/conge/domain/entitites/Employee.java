package tn.conge.domain.entitites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Conge> conges;

    @OneToOne
    private Contract contract;
}