package tn.conge.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.conge.domain.entitites.User;
import tn.conge.domain.exceptions.exceptions.UserNotFoundException;
import tn.conge.domain.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserApplicationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = this.userRepository.findByPhone(phone).orElseThrow(UserNotFoundException::new);
        return UserDetailsMapper.userToUserDetails(user);
    }
}
