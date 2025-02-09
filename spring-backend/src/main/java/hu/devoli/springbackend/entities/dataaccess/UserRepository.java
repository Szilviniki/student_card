package hu.devoli.springbackend.entities.dataaccess;


import hu.devoli.springbackend.entities.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    User findByEmail(String email);

    Optional<User> findById(UUID id);

}
