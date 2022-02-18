package TrekkProject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestRepo testRepo;
    @GetMapping("/hello")
    public String hello(){
        return "Hello word";
    }
}
