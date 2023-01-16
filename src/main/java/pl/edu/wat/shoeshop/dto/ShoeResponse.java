package pl.edu.wat.shoeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;
import pl.edu.wat.shoeshop.entity.Owner;

@Data
@AllArgsConstructor
public class ShoeResponse {
    private String id;
    private String name;
    private OwnerResponse owner;
}
