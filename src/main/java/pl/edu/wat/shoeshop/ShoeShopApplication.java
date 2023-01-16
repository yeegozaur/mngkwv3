package pl.edu.wat.shoeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.shoeshop.reflection.FieldInformation;
import pl.edu.wat.shoeshop.reflection.Reflection;
//import pl.edu.wat.shoeshop.reflection.Reflection;

@SpringBootApplication
public class ShoeShopApplication {

    public static void main(String[] args) throws Exception {
        FieldInformation fieldInformation = new FieldInformation();
        Reflection.apply(fieldInformation.getFieldName(),fieldInformation.getFieldType());
        SpringApplication.run(ShoeShopApplication.class, args);
    }

}