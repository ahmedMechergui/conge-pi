package tn.conge.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.conge.core.security.UserRole;
import tn.conge.core.security.jwt.TokenManager;
import tn.conge.core.security.jwt.TokenType;
import tn.conge.core.sms.senders.VerificationCodeSmsSender;
import tn.conge.domain.api.vm.JWTToken;
import tn.conge.domain.code.ValidationCode;
import tn.conge.domain.code.VerificationCode;
import tn.conge.domain.code.VerificationCodeChecker;
import tn.conge.domain.commons.Checker;
import tn.conge.domain.entitites.User;
import tn.conge.domain.exceptions.exceptions.UserBannedException;
import tn.conge.domain.exceptions.exceptions.UserNotFoundException;
import tn.conge.domain.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    public void loginOrRegister(String phone) {
        Optional<User> userOptional = userRepository.findByPhone(phone);
        User user;
        if (userOptional.isEmpty()) {
            user = new User();
            user.setPhone(phone);
            user.setRole(UserRole.ROLE_CLIENT);
        }else {
            user = userOptional.get();
            this.checkIfUserBanned(user);
        }
        ValidationCode validationCode = new ValidationCode(VerificationCode.generateRandomKey());
        user.setValidationCode(validationCode);
        this.userRepository.save(user);

        VerificationCodeSmsSender smsSender = new VerificationCodeSmsSender(phone,validationCode.getCode());
        smsSender.send();
    }

    public JWTToken validateLogin(String phone,int code){
        User user = this.userRepository.findByPhone(phone).orElseThrow(UserNotFoundException::new);

        Checker checker = new VerificationCodeChecker(code,user.getValidationCode());
        checker.performChecks(true);

        user.setValidationCode(null);
        this.userRepository.save(user);

        return this.tokenManager.generateAccessAndRefreshToken(phone);
    }

    public JWTToken refreshToken(String refreshToken) {
        refreshToken = this.tokenManager.extractToken(refreshToken);
        this.tokenManager.validateToken(refreshToken, TokenType.REFRESH_TOKEN);
        String userPhone = this.tokenManager.getUserPhoneByToken(refreshToken, TokenType.REFRESH_TOKEN);
        return this.tokenManager.generateAccessAndRefreshToken(userPhone);
    }

    public void checkIfUserBanned(User user) {
        if (user.isBanned()) throw new UserBannedException();
    }
}
