package tn.conge.core.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tn.conge.core.properties.CongeProperties;
import tn.conge.core.security.UserApplicationService;
import tn.conge.domain.api.vm.JWTToken;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static tn.conge.core.constants.SecurityConstants.AUTHORITIES_KEY;
import static tn.conge.core.constants.SecurityConstants.BEARER_PREFIX;


@Component
public class TokenManager {

    protected final Key accessTokenKey;
    protected final Key refreshTokenKey;
    protected final CongeProperties properties;
    private final UserApplicationService userApplicationService;


    @Autowired
    public TokenManager(UserApplicationService userApplicationService, CongeProperties congeProperties) {
        this.userApplicationService = userApplicationService;
        this.properties = congeProperties;
        this.accessTokenKey = Keys.hmacShaKeyFor(this.properties.getSecurity().getToken().getSecretKey().getBytes());
        this.refreshTokenKey = Keys.hmacShaKeyFor(this.properties.getSecurity().getToken().getSecretKey().getBytes());

    }


    private String generateAccessToken(String phone) {
        UserDetails user = userApplicationService.loadUserByUsername(phone);
        return BEARER_PREFIX + Jwts.builder()
                .setSubject(user.getUsername())
                .claim(AUTHORITIES_KEY, this.getAuthorities(user))
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.addMinutes(new Date(), this.properties.getSecurity().getToken().getAccessTokenExpiration()))
                .signWith(accessTokenKey)
                .compact();
    }


    protected List<String> getAuthorities(UserDetails admin) {
        return admin.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private String generateRefreshToken(String phone) {
        UserDetails user = userApplicationService.loadUserByUsername(phone);
        return BEARER_PREFIX + Jwts.builder()
                .setSubject(user.getUsername())
                .claim(AUTHORITIES_KEY, this.getAuthorities(user))
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.addMinutes(new Date(), this.properties.getSecurity().getToken().getRefreshTokenExpiration()))
                .signWith(refreshTokenKey)
                .compact();
    }

    public void validateToken(String token, TokenType tokenType) {
        token = this.extractToken(token);
        Key key = getKeyByTokenType(tokenType);
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    public String extractToken(@NonNull String authorizationHeader) {
        if (!authorizationHeader.startsWith(BEARER_PREFIX)) return authorizationHeader;
        return authorizationHeader.substring(BEARER_PREFIX.length());
    }

    public JWTToken generateAccessAndRefreshToken(String phone) {
        return new JWTToken(this.generateAccessToken(phone), this.generateRefreshToken(phone));
    }

    public String getUserPhoneByToken(String token, TokenType tokenType) {
        token = this.extractToken(token);
        Key key = this.getKeyByTokenType(tokenType);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


    public Key getKeyByTokenType(TokenType tokenType) {
        return (tokenType == TokenType.ACCESS_TOKEN) ? accessTokenKey : refreshTokenKey;
    }
}

