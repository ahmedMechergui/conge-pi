package tn.conge.core.security.jwt;

import lombok.Getter;
import lombok.Setter;
import tn.conge.domain.validators.phone.TunisianPhone;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginVM {
    @NotNull
    @TunisianPhone
    private String phone;
    @NotNull
    private Integer code;
}
