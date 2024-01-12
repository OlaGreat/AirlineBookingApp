package AirlineApp.security.services;

import AirlineApp.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AirlineUserDetailsService implements UserDetailsService {

    private PassengerService passengerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        




        return null;
    }
}
