package tn.conge.core.properties;

import lombok.Data;

@Data
public class SecurityProperties {
    private Token token = new Token();

    @Data
    public static class Token{
        /**
         * access token expiration time in minutes
         */
        private int accessTokenExpiration;
        /**
         * refresh token expiration time in minutes
         */
        private int refreshTokenExpiration;
        private String secretKey;
    }
}
