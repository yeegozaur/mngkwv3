package pl.edu.wat.shoeshop.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.shoeshop.service.ScriptService;

@RestController
@CrossOrigin
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @Autowired
    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PutMapping()
    public ResponseEntity<String> execScript() {
        String script= """
                 var Shoe = Java.type('pl.edu.wat.shoeshop.entity.Shoe');
                 var Owner = Java.type('pl.edu.wat.shoeshop.entity.Owner');
                 var Set = Java.type('java.util.Set');
                 
                 function location_owner(){
                 
                 for(owner of ownerRepository.findAll()){   
                 var location = "WAT";
                 owner.setLocation(location);
                 ownerRepository.save(owner);
                 }
                 return ownerRepository.findAll();
                 }
                 location_owner();
                """;
        return new ResponseEntity<>(scriptService.exec(script), HttpStatus.OK) ;
    }
}