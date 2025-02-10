package hu.devoli.springbackend.controllers;


import hu.devoli.springbackend.entities.User;
import hu.devoli.springbackend.entities.dataaccess.UserRepository;
import hu.devoli.springbackend.misc.NewUser;
import hu.devoli.springbackend.records.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicController {

    private final UserRepository userRepository;

    public publicController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String hello() {
        return "Hello, you're on the public route!";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody NewUser newUser) {
        if(userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        BCryptPasswordEncoder bp = new BCryptPasswordEncoder(12);
        User u = new User(newUser.getName(), newUser.getEmail(), bp.encode(newUser.getPassword()));
        userRepository.save(u);
        return new ResponseEntity<>("User registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User u = userRepository.findByEmail(loginRequest.email()).orElse(null);
        if (u == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        BCryptPasswordEncoder bp = new BCryptPasswordEncoder(12);
        if (bp.matches(loginRequest.password(), u.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

}
