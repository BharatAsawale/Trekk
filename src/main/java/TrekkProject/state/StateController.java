package TrekkProject.state;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trekk.trekk.state.response.StateEng;
import trekk.trekk.state.response.StateMar;

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
        List<StateEng> stateEngs=new ArrayList<>();
        for(State state:list){
            StateEng stateEng=new StateEng();
            stateEng.setId(state.getId());
            stateEng.setState(state.getStateNameEn());
            stateEng.setImg(state.getImg());
            stateEngs.add(stateEng);
        }
        return new ResponseEntity<>(stateEngs,HttpStatus.OK);
    }

    @GetMapping("/mar/all")
    public ResponseEntity<?> allMar(){
        List<State> list=stateRepo.findAll();
        List<StateMar> stateMars=new ArrayList<>();
        for(State state:list){
            StateMar stateMar=new StateMar();
            stateMar.setId(state.getId());
            stateMar.setState(state.getStateNameMr());
            stateMar.setImg(state.getImg());
            stateMars.add(stateMar);
        }
        return new ResponseEntity<>(stateMars,HttpStatus.OK);
    }
}
