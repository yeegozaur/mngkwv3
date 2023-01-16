package pl.edu.wat.shoeshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration      //zastępuje xml
@OpenAPIDefinition(info = @Info(title = "ShoeShop API", version = "v1"))
public class OpenAPiConfig {
}