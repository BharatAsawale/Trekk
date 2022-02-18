package TrekkProject.security;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,Integer> {
    Optional<User> findUserByEmail(String email);

    boolean existsById(String userId);

    User findUserById(String userId);

    boolean existsByEmail(String email);
}
