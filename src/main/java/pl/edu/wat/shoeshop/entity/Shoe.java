package pl.edu.wat.shoeshop.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
public class Shoe {
    @MongoId
    private String id;
    private String name;
    private String ownerId;
}
