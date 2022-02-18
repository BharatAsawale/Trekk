package TrekkProject.city;

import TrekkProject.state.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import trekk.trekk.city.response.CityEng;
import trekk.trekk.city.response.CityMar;

import java.util.List;

@Repository
public interface CityRepo extends MongoRepository<City,Long> {
    List<CityEng> findAllCitiesByState(State state);
    List<CityMar> findAllByState(State state);
    City findById(int id);


}
