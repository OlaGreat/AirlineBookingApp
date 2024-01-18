package AirlineApp.security;

import AirlineApp.security.filter.AirlineAuthenticationFilter;
import AirlineApp.security.filter.AirlineAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static AirlineApp.data.models.Role.ADMIN;
import static AirlineApp.data.models.Role.PASSENGER;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        final String [] publicEndPoint = {"api/v1/passenger", "/login", "api/v1/admin", "/login", "api/v1/company", "/login"};
        return httpSecurity
                .addFilterAt(new AirlineAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AirlineAuthorizationFilter(), AirlineAuthenticationFilter.class)
                .sessionManagement(customizer->customizer.sessionCreationPolicy(STATELESS))
                .csrf(c->c.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(c->c.requestMatchers(publicEndPoint).permitAll())
                .authorizeHttpRequests(c->c.anyRequest().hasAuthority(PASSENGER.name()))
                .build();

    }

}
