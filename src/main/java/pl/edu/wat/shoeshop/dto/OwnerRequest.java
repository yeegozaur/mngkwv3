package pl.edu.wat.shoeshop.dto;

import lombok.Data;

@Data
public class OwnerRequest {
    private String name;
    private String surname;
    private String pseudonym;
}
