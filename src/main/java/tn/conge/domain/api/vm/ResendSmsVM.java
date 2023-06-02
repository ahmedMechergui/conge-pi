package tn.conge.domain.api.vm;

import lombok.Data;
import tn.conge.domain.enums.SmsType;
import tn.conge.domain.validators.phone.TunisianPhone;


import javax.validation.constraints.NotNull;

@Data
public class ResendSmsVM {
    @TunisianPhone
    @NotNull
    private String phone;
    @NotNull
    private SmsType smsType;
}
