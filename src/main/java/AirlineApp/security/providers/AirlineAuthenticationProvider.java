package AirlineApp.security.providers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static AirlineApp.exceptions.ExceptionMessages.INVALID_CREDENTIAL_EXCEPTION;

@Component
@AllArgsConstructor
public class AirlineAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String providedPassword = authentication.getCredentials().toString();

        UserDetails foundUser = userDetailsService.loadUserByUsername(email);
        String encodePassword = foundUser.getPassword();

        boolean isValidPassword = passwordEncoder.matches(providedPassword, encodePassword);

        if(isValidPassword){
            Collection<? extends GrantedAuthority> authorities = foundUser.getAuthorities();
            Authentication authenticationResult = new UsernamePasswordAuthenticationToken(email, providedPassword, authorities);
            return authenticationResult;
        }


        throw new BadCredentialsException(INVALID_CREDENTIAL_EXCEPTION.getMessage());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
