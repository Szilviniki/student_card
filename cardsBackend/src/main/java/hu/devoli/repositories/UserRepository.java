package hu.devoli.repositories;

import hu.devoli.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(String id);

    User save(@NotBlank User user);

    void deleteById(String id);

    List<User> findAll();

    int update(@NotBlank String id, @NotNull User user);

    Optional<User> findByEmail(@NotBlank String email);

    int updatePassword(@NotBlank String email, @NotBlank String password);
}
