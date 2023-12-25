package AirlineApp.util;

import AirlineApp.exceptions.UnauthorizedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;
import java.util.Map;

import static AirlineApp.exceptions.ExceptionMessages.INVALID_AUTHORIZATION_HEADER;
import static AirlineApp.exceptions.ExceptionMessages.VERIFICATION_FAILED_EXCEPTION;
import static AirlineApp.util.AppUtils.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtUtils {

    public static String generateVerificationToken(String userEmail) {
        String token = JWT.create()
                .withClaim(USER, userEmail)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512(SECRET_KEY));
        return token;
    }

    public static String verifyToken(HttpServletRequest request) throws UnauthorizedException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith(BEARER)){
            String authorizationToken = authorizationHeader.substring(SEVEN);
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                    .withIssuer(APP_NAME)
                    .withClaimPresence(USER)
                    .build();

            DecodedJWT verifiedToken = verifier.verify(authorizationToken);

            if(verifiedToken != null){
                return authorizationToken;
            }
            throw new UnauthorizedException(VERIFICATION_FAILED_EXCEPTION.getMessage());
        }
        else throw new UnauthorizedException(INVALID_AUTHORIZATION_HEADER.getMessage());
    }

    public static String extractUserEmailFrom(String token){
        var claim = JWT.decode(token).getClaim(USER);
        String userEmail = (String) claim.asMap().get(USER);
        return userEmail;
    }

    public static String extractUserEmailFrom2(String token) throws UnauthorizedException {
        DecodedJWT decodedJWT = JWT.decode(token);

        Map<String, Claim> claimMap = decodedJWT.getClaims();
        if(claimMap.containsKey(USER)){
            return claimMap.get(USER).asString();
        }
        throw new UnauthorizedException(VERIFICATION_FAILED_EXCEPTION.getMessage());

    }
}