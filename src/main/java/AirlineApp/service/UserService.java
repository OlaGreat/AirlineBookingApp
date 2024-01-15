package AirlineApp.service;

import AirlineApp.data.models.User;

public interface UserService {
    User findUserByEmail (String userEmail);
}
