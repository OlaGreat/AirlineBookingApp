package AirlineApp.security.filter;

import AirlineApp.dtos.request.LoginRequest;
import AirlineApp.dtos.response.ApiResponse;
import AirlineApp.util.AppUtils;
import AirlineApp.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class AirlineAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
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

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {

        Collection<? extends GrantedAuthority> userAuthorities = authResult.getAuthorities();

        List<? extends GrantedAuthority> authorities = new ArrayList<>(userAuthorities);

        var roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = JwtUtils.generateAccessToken(roles);

        var apiResponse = ApiResponse.builder().data(token).build();

        response.setContentType(APPLICATION_JSON_VALUE);

        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }

}
