package tn.conge.domain.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.conge.core.security.AuthFacade;
import tn.conge.core.security.authorizations.UserAccess;
import tn.conge.core.security.jwt.LoginVM;
import tn.conge.domain.api.dtos.UserDto;
import tn.conge.domain.api.mappers.UserMapper;
import tn.conge.domain.api.vm.JWTToken;
import tn.conge.domain.api.vm.PhoneVM;
import tn.conge.domain.api.vm.RefreshTokenVM;
import tn.conge.domain.entitites.User;
import tn.conge.domain.services.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthFacade authFacade;
    private final UserMapper userMapper;

    @PostMapping("login")
    public ResponseEntity<Void> loginOrRegister(@RequestBody @Valid PhoneVM phoneVM) {
        this.authService.loginOrRegister(phoneVM.getPhone());
        return ResponseEntity.ok().build();
    }

    @PostMapping("validate")
    public ResponseEntity<JWTToken> validateLogin(@RequestBody @Valid LoginVM loginVM) {
        return ResponseEntity.ok(this.authService.validateLogin(loginVM.getPhone(), loginVM.getCode()));
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTToken> getRefreshToken(@RequestBody RefreshTokenVM refreshTokenVM) {
        return ResponseEntity.ok(this.authService.refreshToken(refreshTokenVM.getRefreshToken()));
    }

    @GetMapping("me")
    @UserAccess
    public ResponseEntity<UserDto> getAuthenticatedUser() {
        User user = this.authFacade.getAuthenticated();
        this.authService.checkIfUserBanned(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
