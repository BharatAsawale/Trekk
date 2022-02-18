package TrekkProject.city;

import TrekkProject.state.State;
import TrekkProject.state.StateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trekk.trekk.city.response.CityEng;
import trekk.trekk.city.response.CityMar;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {
    private final CityRepo cityRepo;
    private final StateRepo stateRepo;

    @PostMapping("/add/{sid}")
    public ResponseEntity<?> AddCity(@RequestBody City city, @PathVariable int sid){
        State state=stateRepo.findById(sid);
        if(state==null)
            return ResponseEntity.badRequest().body("State Not Found");
        city.setState(state);
        City city1=new City();
        city1=cityRepo.save(city);
        return new ResponseEntity<>(city1,HttpStatus.OK);
    }

    @GetMapping("/alldata")
    public ResponseEntity<?> all(){
        return new ResponseEntity<>(cityRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("eng/all")
    public ResponseEntity<?> allCities(){
        List<City> list=cityRepo.findAll();
        List<CityEng> list1=new ArrayList<>();
        for(City city:list){
            CityEng cityEng=new CityEng();
            cityEng.setId(city.getId());
            cityEng.setCity(city.getCityNameEn());
            list1.add(cityEng);
        }
        return new ResponseEntity<>(list1,HttpStatus.OK);
    }

    @GetMapping("mar/all")
    public ResponseEntity<?> allMarCities(){
        List<City> list=cityRepo.findAll();
        List<CityMar> list1=new ArrayList<>();
        for(City city:list){
            CityMar cityMar=new CityMar();
            cityMar.setId(city.getId());
            cityMar.setCity(city.getCityNameMr());
            list1.add(cityMar);
        }
        return new ResponseEntity<>(list1,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable int id) {
        return new ResponseEntity<>(cityRepo.findById(id),HttpStatus.OK);
    }

//    @GetMapping("/state/{sid}")
//    public ResponseEntity<?> findCitiesById(@PathVariable int sid){
//        State state=stateRepo.findById(sid);
//        List<City> cities=cityRepo.findAllCitiesByState(state);
//        return new ResponseEntity<>(cities,HttpStatus.OK);
//    }

    @GetMapping("/state/eng/{sid}")
    public ResponseEntity<?> findByIdEng(@PathVariable int sid){
        State state=stateRepo.findById(sid);
        List<CityEng> cityEngs=cityRepo.findAllCitiesByState(state);
        return new ResponseEntity<>(cityEngs,HttpStatus.OK);
    }

    @GetMapping("/state/mar/{sid}")
    public ResponseEntity<?> findByIdMar(@PathVariable int sid){
        State state=stateRepo.findById(sid);
        List<CityMar> cityMar=cityRepo.findAllByState(state);
        return new ResponseEntity<>(cityMar,HttpStatus.OK);
    }

    @GetMapping("/city/addallcity")
    public String addAllCity(){
        String[] dis={"Ahmednagar","Akola","Amravati","Aurangabad","Beed","Bhandara","Buldhana","Chandrapur",
                "Dhule","Gadchiroli","Gondia","Hingoli","Jalgaon","Jalna","Kolhapur","Latur","Mumbai City",
                "Mumbai Suburban","Nagpur","Nanded","Nandurbar","Nashik","Osmanabad","Palghar","Parbhani",
                "Pune","Raigad","Ratnagiri","Sangli","Satara","Sindhudurg","Solapur","Thane","Wardha",
                "Washim","Yavatmal"};
        String[] disMr={"अहमदनगर","अकोला","अमरावती","औरंगाबाद","बीड","भंडारा","बुलढाणा","चंद्रपूर",
                "धुळे","गडचिरोली","गोंदिया","हिंगोली","जळगाव","जालना","कोल्हापूर","लातूर",
                "मुंबई उपनगर","मुंबई शहर","नागपूर","नांदेड","नंदुरबार","नाशिक","उस्मानाबाद","पालघर",
                "परभणी","पुणे","रायगड","रत्‍नागिरी","सांगली","सातारा","सिंधुदुर्ग","सोलापूर",
                "ठाणे","वर्धा","वाशीम","यवतमाळ"};
        int count=101;
        State state=stateRepo.findById(1);
        if(state==null)
            return "State Not Found";
        int i=0;
        for(String d:dis){
            City city=new City();
            city.setId(count++);
            city.setCityNameEn(d);
            city.setCityNameMr(disMr[i++]);
            city.setState(state);
            cityRepo.save(city);
        }
        return "done";
    }

}
