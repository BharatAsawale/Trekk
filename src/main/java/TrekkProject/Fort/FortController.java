package TrekkProject.Fort;

import TrekkProject.Fort.response.FortEng;
import TrekkProject.Fort.response.FortMar;
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

    @GetMapping("/eng/city/{id}")
    public ResponseEntity<?> findFortByCity(@PathVariable int id){
        City city=cityRepo.findById(id);
        if(city==null)
            return ResponseEntity.badRequest().body("no data found for given city");
        List<Fort> fortList=fortRepo.findByCity(city);
        List<FortEng> fortEngList=new ArrayList<>();
        for (Fort fort:fortList){
            FortEng fortEng=new FortEng();
            fortEng.setId(fort.getId());
            fortEng.setFortName(fort.getFortName());
            fortEng.setFeatures(fort.getFeatures());
            fortEng.setTypeOfFort(fort.getTypeOfFort());
            fortEng.setHeight(fort.getHeight());
            fortEng.setHistory(fort.getHistory());
            fortEng.setMapUrl(fort.getMapUrl());
            fortEng.setTransportFacility(fort.getTransportFacility());
            fortEng.setStayFacility(fort.getStayFacility());
        }
        return new ResponseEntity<>(fortEngList,HttpStatus.OK);
    }

    @GetMapping("/mar/city/{id}")
    public ResponseEntity<?> findFortMar(@PathVariable int id){
        City city=cityRepo.findById(id);
        if(city==null){
            return ResponseEntity.badRequest().body("City not found");
        }
        List<Fort> fortList=fortRepo.findByCity(city);
        List<FortMar> fortMarList=new ArrayList<>();
        for(Fort fort:fortList){
            FortMar fortMar=new FortMar();
            fortMar.setId(fort.getId());
            fortMar.setFortNameMr(fort.getFortNameMr());
            fortMar.setFeaturesMr(fort.getFeaturesMr());
            fortMar.setTypeOfFortMr(fort.getTypeOfFortMr());
            fortMar.setHeightMr(fort.getHeightMr());
            fortMar.setHistoryMr(fort.getHistoryMr());
            fortMar.setMapUrlMr(fort.getMapUrlMr());
            fortMar.setTransportFacilityMr(fort.getTransportFacilityMr());
            fortMar.setStayFacilityMr(fort.getStayFacilityMr());

            fortMarList.add(fortMar);
        }
        return new ResponseEntity<>(fortMarList,HttpStatus.OK);
    }
}
