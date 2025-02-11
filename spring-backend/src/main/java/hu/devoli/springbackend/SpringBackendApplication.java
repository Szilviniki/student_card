package hu.devoli.springbackend;

import hu.devoli.springbackend.entities.User;
import hu.devoli.springbackend.entities.dataaccess.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class SpringBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBackendApplication.class, args);

    }

//    @Bean
//    public CorsConfigurationSource corsConfigSource (){
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("http://localhost:3000");
//        corsConfiguration.addAllowedOrigin("https://localhost:3000");
//        corsConfiguration.addAllowedOrigin("https://localhost");
//        corsConfiguration.addAllowedMethod("*");
//
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        CorsConfigurationSource corsConfigS = corsConfigSource();
//        http.cors(cors -> cors.configurationSource(corsConfigS));
//
//        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
////                        .requestMatchers("/public/**").permitAll()
//                        .anyRequest().permitAll()
//                ).csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }


    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new User("John Doe", "johndoe@example.com", "password"));
            repository.save(new User("Jane Doe", "janedoe@example.com", "password"));
            repository.save(new User("Alice Smith", "alicesmith@example.com", "password"));
            repository.save(new User("Bob Johnson", "bobjohnson@example.com", "password"));
            repository.save(new User("Charlie Brown", "charliebrown@example.com", "password"));

        };
    }

}

