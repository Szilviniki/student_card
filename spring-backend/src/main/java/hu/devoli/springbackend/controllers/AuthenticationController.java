package hu.devoli.springbackend.controllers;


import hu.devoli.springbackend.entities.User;
import hu.devoli.springbackend.entities.dataaccess.UserRepository;
import hu.devoli.springbackend.records.LoginResponse;
import hu.devoli.springbackend.records.RegistrationRequest;
import hu.devoli.springbackend.records.LoginRequest;
import hu.devoli.springbackend.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class AuthenticationController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthenticationController(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping()
    public String hello() {
        return "Hello, you're on the public route!";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) {
        if(userRepository.findByEmail(registrationRequest.email()).isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        BCryptPasswordEncoder bp = new BCryptPasswordEncoder(12);
        User u = new User(registrationRequest.name(), registrationRequest.email(), bp.encode(registrationRequest.password()));
        userRepository.save(u);
        return new ResponseEntity<>("User registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(details);
        LoginResponse res = new LoginResponse(token);

        return ResponseEntity.ok().body(res);
    }

}
