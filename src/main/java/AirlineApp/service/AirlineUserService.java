package AirlineApp.service;

import AirlineApp.data.models.User;
import AirlineApp.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static AirlineApp.dtos.response.ResponseMessage.USER_WITH_EMAIL_NOT_FOUND;


@Service
@AllArgsConstructor
public class AirlineUserService implements UserService{
    private final UserRepository userRepository;

    @Override
    public User findUserByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new UsernameNotFoundException(
                String.format(USER_WITH_EMAIL_NOT_FOUND.getMessage(), userEmail)
        ));
        return user;
    }
}
