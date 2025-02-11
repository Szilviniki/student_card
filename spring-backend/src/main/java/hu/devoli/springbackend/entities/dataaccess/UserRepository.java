package hu.devoli.springbackend.entities.dataaccess;


import hu.devoli.springbackend.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByEmail(String email);

}
