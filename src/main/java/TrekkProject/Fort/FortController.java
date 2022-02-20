package TrekkProject.Fort;

import TrekkProject.city.City;
import TrekkProject.city.CityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/fort")
public class FortController {
    private final FortRepo fortRepo;
    private final CityRepo cityRepo;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Fort fort){
        int cid=fort.getCityId();
        City city=cityRepo.findById(cid);
        if(city==null){
            return ResponseEntity.badRequest().body("City not found!..");
        }
        fort.setCity(city);
        Fort fort1=fortRepo.save(fort);
        return new ResponseEntity<>(fort1, HttpStatus.OK);
    }

    @GetMapping("/alldata")
    public ResponseEntity<List<Fort>> allData(){
        List<Fort> list=fortRepo.findAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findFort(@PathVariable int id){
        Fort fort=new Fort();
        fort = fortRepo.findById(id);
        if (fort==null)
            return ResponseEntity.badRequest().body("Fort not found with id");
        return new ResponseEntity<>(fortRepo.findById(id),HttpStatus.OK);
    }

    @GetMapping("/eng/{id}")
    public ResponseEntity<?> findByIdEN(@PathVariable int id){
        Fort fort=fortRepo.findById(id);
        FortResponse fortResponse=new FortResponse();
        fortResponse.setFortName(fort.getFortName());
        fortResponse.setTypeOfFort(fort.getTypeOfFort());
        fortResponse.setFeatures(fort.getFeatures());
        fortResponse.setHeight(fort.getHeight());
        fortResponse.setHistory(fort.getHistory());
        fortResponse.setStayFacility(fort.getStayFacility());
        fortResponse.setImg(fort.getImg());
        return new ResponseEntity<>(fortResponse,HttpStatus.OK);
    }

    @GetMapping("/city/eng/{id}")
    public ResponseEntity<?> findFortByCity(@PathVariable int id){
        City city=cityRepo.findById(id);
        if(city==null)
            return ResponseEntity.badRequest().body("no data found for given city");
        List<Fort> fortList=fortRepo.findByCity(city);
        List<FortResponse> list=new ArrayList<>();
        for (Fort fort:fortList){
            FortResponse fortResponse=new FortResponse();
            fortResponse.setId(fort.getId());
            fortResponse.setFortName(fort.getFortName());
            fortResponse.setFeatures(fort.getFeatures());
            fortResponse.setTypeOfFort(fort.getTypeOfFort());
            fortResponse.setHeight(fort.getHeight());
            fortResponse.setHistory(fort.getHistory());
            fortResponse.setMapUrl(fort.getMapUrl());
            fortResponse.setTransportFacility(fort.getTransportFacility());
            fortResponse.setStayFacility(fort.getStayFacility());
            list.add(fortResponse);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/city/mar/{id}")
    public ResponseEntity<?> findFortByCityMr(@PathVariable int id){
        City city=cityRepo.findById(id);
        if(city==null)
            return ResponseEntity.badRequest().body("no data found for given city");
        List<Fort> fortList=fortRepo.findByCity(city);
        List<FortResponse> list=new ArrayList<>();
        for (Fort fort:fortList){
            FortResponse fortResponse=new FortResponse();
            fortResponse.setId(fort.getId());
            fortResponse.setFortName(fort.getFortNameMr());
            fortResponse.setFeatures(fort.getFeaturesMr());
            fortResponse.setTypeOfFort(fort.getTypeOfFortMr());
            fortResponse.setHeight(fort.getHeightMr());
            fortResponse.setHistory(fort.getHistoryMr());
            fortResponse.setTransportFacility(fort.getTransportFacilityMr());
            fortResponse.setStayFacility(fort.getStayFacilityMr());
            fortResponse.setImg(fort.getImg());
            fortResponse.setMapUrl(fort.getMapUrl());
            list.add(fortResponse);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
