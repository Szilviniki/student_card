package hu.devoli.repositories;

import hu.devoli.domain.User;
import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserRepositoryImpl implements UserRepository {
    private static final List<String> VALID_PROPERTIES = Arrays.asList("id", "first_name","last_name", "email", "password");
    
    private final EntityManager entityManager;
    
    private final ApplicationConfiguration applicationConfiguration;
    
    public UserRepositoryImpl(EntityManager entityManager, ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @ReadOnly
    public Optional<User> findById(String id){
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public User save(@NotBlank User user){
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteById(String id){
        findById(id).ifPresent(entityManager::remove);
    }

    @Override
    @ReadOnly
    public List<User> findAll(){
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public int update(@NotBlank String id, @NotBlank User user){
        User existingUser = entityManager.find(User.class, id);
        if (existingUser == null) {
            return 0;
        }
        VALID_PROPERTIES.forEach(property -> {
            switch (property) {
                case "first_name":
                    existingUser.setFirstName(user.getFirstName());
                    break;
                case "last_name":
                    existingUser.setLastName(user.getLastName());
                    break;
                case "email":
                    existingUser.setEmail(user.getEmail());
                    break;
                case "password":
                    existingUser.setPassword(user.getPassword());
                    break;
            }
        });
        entityManager.persist(existingUser);
        return 1;
    }

    @Override
    @ReadOnly
    public Optional<User> findByEmail(@NotBlank String email){
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
    @Override
    @Transactional
    public int updatePassword(@NotBlank String email, @NotBlank String password) {
        Optional<User> existingUserOptional = findByEmail(email);
        if (existingUserOptional.isEmpty()) {
            return 0;
        }
        User existingUser = existingUserOptional.get();
        existingUser.setPassword(password);
        entityManager.persist(existingUser);
        return 1;
    }

}
