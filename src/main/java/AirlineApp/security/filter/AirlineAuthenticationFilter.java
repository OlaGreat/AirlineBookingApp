package AirlineApp.security.filter;

import AirlineApp.dtos.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class AirlineAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    ObjectMapper objectMapper = new ObjectMapper();


    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        try {
            InputStream inputStream = request.getInputStream();
            LoginRequest loginRequest = objectMapper.readValue(inputStream, LoginRequest.class);

            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            Authentication toAuthenticate = new UsernamePasswordAuthenticationToken(email, password);

            Authentication authenticationResult = authenticationManager.authenticate(toAuthenticate);
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);

            return authenticationResult;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
