package tn.conge.domain.entitites;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import tn.conge.core.security.UserRole;
import tn.conge.domain.code.ValidationCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@FieldNameConstants
@Table(name = "user", indexes = {
        @Index(name = "idx_user_phone", columnList = "phone"), @Index(name = "idx_user_email", columnList = "email")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends BaseEntity {
    @Column(unique = true, updatable = false, nullable = false)
    @NotNull
    private String phone;

    @Column(unique = true, updatable = false, nullable = false)
    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "validation_code_id")
    private ValidationCode validationCode;

    @Column
    private boolean banned = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equal(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(phone);
    }
}
