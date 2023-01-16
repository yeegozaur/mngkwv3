package pl.edu.wat.shoeshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.shoeshop.entity.Owner;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
}
