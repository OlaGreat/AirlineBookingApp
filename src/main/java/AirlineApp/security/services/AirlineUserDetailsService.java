package AirlineApp.security.services;

import AirlineApp.data.models.User;
import AirlineApp.exceptions.UserNotFoundException;
import AirlineApp.security.model.SecureUser;
import AirlineApp.service.PassengerService;
import AirlineApp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AirlineUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findUserByEmail(username);
        UserDetails userDetails = new SecureUser(user);
        return userDetails;
    }
}
