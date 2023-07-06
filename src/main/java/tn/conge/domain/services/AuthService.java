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
import tn.conge.domain.entitites.Employee;
import tn.conge.domain.entitites.User;
import tn.conge.domain.exceptions.exceptions.EntityNotFoundException;
import tn.conge.domain.exceptions.exceptions.UserBannedException;
import tn.conge.domain.exceptions.exceptions.UserNotFoundException;
import tn.conge.domain.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    public void login(String phone) {
        User user = userRepository.findByPhone(phone).orElseThrow(() -> new EntityNotFoundException(User.class));
        this.checkIfUserBanned(user);
        ValidationCode validationCode = new ValidationCode(VerificationCode.generateRandomKey());
        user.setValidationCode(validationCode);
        this.userRepository.save(user);
        VerificationCodeSmsSender smsSender = new VerificationCodeSmsSender(phone, validationCode.getCode());
        smsSender.send();
    }

    public void register(String phone,String email,String firstName,String lastName,UserRole role){
        Employee user = new Employee();
        user.setRole(role);
        user.setPhone(phone);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        ValidationCode validationCode = new ValidationCode(VerificationCode.generateRandomKey());
        user.setValidationCode(validationCode);
        this.userRepository.save(user);

        VerificationCodeSmsSender smsSender = new VerificationCodeSmsSender(phone, validationCode.getCode());
        smsSender.send();


    }

    public JWTToken validateLogin(String phone, int code) {
        User user = this.userRepository.findByPhone(phone).orElseThrow(UserNotFoundException::new);

        Checker checker = new VerificationCodeChecker(code, user.getValidationCode());
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
