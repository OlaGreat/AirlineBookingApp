package AirlineApp.security.manager;

import AirlineApp.exceptions.AuthenticationNotSupportedException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static AirlineApp.exceptions.ExceptionMessages.AUTHENTICATION_NOT_SUPPORTED;

@Component
@AllArgsConstructor
public class AirlineAuthenticationManager implements AuthenticationManager {

    private AuthenticationProvider authenticationProvider;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       try{
           if (authenticationProvider.supports(authentication.getClass())){
               Authentication authResult = authenticationProvider.authenticate(authentication);
               return authResult;
           }
           throw new AuthenticationNotSupportedException(AUTHENTICATION_NOT_SUPPORTED.getMessage());
       } catch (AuthenticationNotSupportedException e) {
           throw new RuntimeException(e);
       }


    }
}
