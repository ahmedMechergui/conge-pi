package tn.conge.core.security;


import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum UserRole {
    /**
     * Default role for new users
     */
    ROLE_USER(Sets.newHashSet(UserAuthority.USER)),
    ROLE_CLIENT(Sets.newHashSet(UserAuthority.CLIENT,UserAuthority.USER)),
    ROLE_DELIVERY_MAN(Sets.newHashSet(UserAuthority.DELIVERY_MAN,UserAuthority.CLIENT,UserAuthority.USER)),
    ROLE_ADMIN(Sets.newHashSet(UserAuthority.ADMIN, UserAuthority.DELIVERY_MAN,UserAuthority.CLIENT ,UserAuthority.USER));

    private final Set<UserAuthority> permissions;

    UserRole(HashSet<UserAuthority> permissions) {
        this.permissions = permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return this.permissions.stream().map(userAuthority -> new SimpleGrantedAuthority(userAuthority.getAuthority())).collect(Collectors.toSet());
    }
}