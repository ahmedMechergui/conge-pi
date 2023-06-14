package tn.conge.domain.Entities;

import lombok.Getter;
import lombok.Setter;
import tn.conge.domain.storage.CustomFile;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DeliveryMan extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private CustomFile photo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn
    private User user;
}
