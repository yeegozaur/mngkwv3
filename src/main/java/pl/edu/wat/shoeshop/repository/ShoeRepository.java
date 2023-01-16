package pl.edu.wat.shoeshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.shoeshop.entity.Shoe;

@Repository
public interface ShoeRepository extends MongoRepository<Shoe, String> {
}
