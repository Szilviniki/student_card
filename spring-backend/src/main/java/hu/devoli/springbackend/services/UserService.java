package hu.devoli.springbackend.services;

import hu.devoli.springbackend.entities.User;
import hu.devoli.springbackend.entities.dataaccess.UserRepository;
import hu.devoli.springbackend.records.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Transient
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<ResponseEntity<UserResponse>> findById(UUID id){
        try {
            java.util.Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                UserResponse res = new UserResponse(user.get().getId(), user.get().getName(), user.get().getUsername());
                return java.util.Optional.of(ResponseEntity.ok(res));
            }else{
                throw new Exception("User not found");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Page<UserResponse> getusersPage(int pageNumber, int pageSize, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
        return userRepository.findAllBy(pageable);
    }

}
