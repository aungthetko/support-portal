package com.demo.supportportal.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.demo.supportportal.constants.SecurityConstants;
import com.demo.supportportal.domain.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import static com.demo.supportportal.constants.SecurityConstants.*;

public class JWTTokenProvider {

    @Value("jwt.secret")
    private String secret;

    public String generateToken(UserPrincipal userPrincipal){
        String[] claims = getClaimsFromUser(userPrincipal);
        return JWT.create().withIssuer(ATK_LLC)
                .withAudience(ATK_ADMINISTRATION )
                .withIssuedAt(new Date())
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(AUTHORITIES, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        return null;
    }
}
