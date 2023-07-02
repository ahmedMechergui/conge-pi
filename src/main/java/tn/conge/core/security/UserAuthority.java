package tn.conge.core.security;

import lombok.Getter;

@Getter
public enum UserAuthority {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    HUMAN_RESOURCES("HUMAN_RESOURCES"),
    USER("USER");

    String authority;

    UserAuthority(String authority) {
        this.authority = authority;
    }
}
