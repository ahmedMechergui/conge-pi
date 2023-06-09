package tn.conge.core.security;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.conge.domain.entitites.User;

import java.util.ArrayList;
import java.util.Collection;


public interface UserDetailsMapper {

    public static UserDetails userToUserDetails(User user) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().getGrantedAuthorities().forEach(grantedAuthority -> authorities.add(new SimpleGrantedAuthority(grantedAuthority.getAuthority())));
        return new org.springframework.security.core.userdetails.User(user.getPhone(), Strings.EMPTY, authorities);
    }

}
