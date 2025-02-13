package hu.devoli.springbackend.entities.dataaccess;


import hu.devoli.springbackend.entities.User;
import hu.devoli.springbackend.records.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query("SELECT new hu.devoli.springbackend.records.UserResponse(u.id, u.name, u.email) FROM User u")
    Page<UserResponse> findAllBy(Pageable pageable);

}
