package hu.devoli.springbackend.controllers;

import hu.devoli.springbackend.entities.User;
import hu.devoli.springbackend.entities.dataaccess.UserRepository;
import hu.devoli.springbackend.records.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;



@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/find")
    public Optional<ResponseEntity<UserResponse>> findById(@RequestParam(value = "id")UUID id){
        try {
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
               UserResponse res = new UserResponse(user.get().getId(), user.get().getName(), user.get().getUsername());
               return Optional.of(ResponseEntity.ok(res));
            }else{
                throw new Exception("User not found");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
