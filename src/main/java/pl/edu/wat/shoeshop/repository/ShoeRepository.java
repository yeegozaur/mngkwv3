package pl.edu.wat.shoeshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.shoeshop.entity.Shoe;


//przekazywane typy <encja, typ_klucza_głównego>
@Repository
public interface ShoeRepository extends MongoRepository<Shoe, String> {         //obiekt, przy pomocy którego
                                                                                // wykonujemy operacje bezpośrednio na bazie danych
}
