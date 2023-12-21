package AirlineApp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;

import static AirlineApp.util.AppUtils.*;

public class JwtUtils {

    public static String generateVerificationToken(String userEmail) {
        String token = JWT.create()
                .withClaim(USER, userEmail)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512(SECRET_KEY));

        return token;

    }




}
