package tn.conge.domain.api.vm;

import lombok.Data;
import tn.conge.core.security.UserRole;
import tn.conge.domain.validators.phone.TunisianPhone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RegisterVM {
    @TunisianPhone
    private String phone;
    @Email
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private UserRole role;
}
