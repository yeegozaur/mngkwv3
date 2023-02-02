package pl.edu.wat.shoeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerResponse {
    private String id;
    private String name;
    private String surname;
    private String pseudonym;
}
