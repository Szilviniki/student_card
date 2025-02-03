package hu.devoli.services;

import hu.devoli.domain.User;
import hu.devoli.repositories.UserRepository;
import jakarta.inject.Singleton;



@Singleton
public class PublicServices {
    private final UserRepository userRepository;


    public PublicServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void RegisterUser(String email, String password) {
        User user = new User();

    }
}
