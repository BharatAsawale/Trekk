package TrekkProject.security;

import TrekkProject.security.requestResponse.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        if (userRepository.existsById(userId)){
            return ResponseEntity.ok().body(userRepository.findUserById(userId));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("user not exits!"));
        }
    }
}
