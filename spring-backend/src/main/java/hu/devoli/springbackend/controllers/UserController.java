package hu.devoli.springbackend.controllers;

import hu.devoli.springbackend.entities.User;
import hu.devoli.springbackend.entities.dataaccess.UserRepository;
import hu.devoli.springbackend.records.UserResponse;
import hu.devoli.springbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;



@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<String> Hello(){
        return ResponseEntity.ok("this is the /users route!");
    }

    @GetMapping("/find")
    public Optional<ResponseEntity<UserResponse>> findById(@RequestParam(value = "id")UUID id){
      return userService.findById(id);
    }

//    @GetMapping("/index")
//    public ResponseEntity<Pageable> Index(@RequestParam(name = "amount", defaultValue = "20") int amount){
//        Iterable<User> queryResults = userRepository.findAll();
//    }
}
