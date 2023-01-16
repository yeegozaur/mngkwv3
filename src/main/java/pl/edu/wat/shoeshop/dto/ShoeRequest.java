package pl.edu.wat.shoeshop.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;
import pl.edu.wat.shoeshop.entity.Owner;

@Data
public class ShoeRequest {
    private String name;
    private String ownerId;
}
