package TrekkProject.state;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/state")
public class StateController {
    private final StateRepo stateRepo;

    @PostMapping(value = "/add")
    public ResponseEntity<State> AddState(@RequestBody State state){
        State state1=new State();
        state1=stateRepo.save(state);
        return new ResponseEntity<>(state1, HttpStatus.OK);
    }

    @GetMapping("eng/all")
    public ResponseEntity<?> allEng(){
        List<State> list=stateRepo.findAll();
        List<StateResponse> responses=new ArrayList<>();
        for(State state:list){
            StateResponse stateResponse=new StateResponse();
            stateResponse.setId(state.getId());
            stateResponse.setState(state.getStateNameEn());
            stateResponse.setImg(state.getImg());
            responses.add(stateResponse);
        }
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @GetMapping("/mar/all")
    public ResponseEntity<?> allMar() {
        List<State> list = stateRepo.findAll();
        List<StateResponse> responses = new ArrayList<>();
        for (State state : list) {
            StateResponse stateResponse = new StateResponse();
            stateResponse.setId(state.getId());
            stateResponse.setState(state.getStateNameMr());
            stateResponse.setImg(state.getImg());
            responses.add(stateResponse);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
