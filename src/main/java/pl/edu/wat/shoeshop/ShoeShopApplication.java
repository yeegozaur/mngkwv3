package pl.edu.wat.shoeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wat.shoeshop.reflection.FieldInformation;
import pl.edu.wat.shoeshop.reflection.Reflection;

@SpringBootApplication
public class ShoeShopApplication {

    public static void main(String[] args) throws Exception {
        FieldInformation fieldInformation = new FieldInformation();
        Reflection.apply(fieldInformation.getFieldName1(),fieldInformation.getFieldType1(),
                fieldInformation.getFieldName2(), fieldInformation.getFieldType2(),
                fieldInformation.getFieldName3(), fieldInformation.getFieldType3());
        SpringApplication.run(ShoeShopApplication.class, args);
    }
}