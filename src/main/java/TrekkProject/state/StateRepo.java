package TrekkProject.state;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepo extends MongoRepository<State,Long> {
    State findById(int sid);
}
