package pl.edu.wat.shoeshop.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class Owner {
    @MongoId
    private String id;
    private String surname;
    private String name;
    private String pseydonym;
}
