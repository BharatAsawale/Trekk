package TrekkProject.Fort;

import TrekkProject.city.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FortRepo extends MongoRepository<Fort,Long> {
    Fort findById(int id);

    List<Fort> findByCity(City city);
}
