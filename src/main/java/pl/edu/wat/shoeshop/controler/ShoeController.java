package pl.edu.wat.shoeshop.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.shoeshop.dto.ShoeRequest;
import pl.edu.wat.shoeshop.dto.ShoeResponse;
import pl.edu.wat.shoeshop.exception.EntityNotFound;
import pl.edu.wat.shoeshop.service.ShoeService;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/shoe")
public class ShoeController {
    private final ShoeService shoeService;

    @Autowired
    public ShoeController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @GetMapping()
    public ResponseEntity<List<ShoeResponse>> getAllShoe() {
        List<ShoeResponse> shoeOptional = shoeService.getAll();
        return new ResponseEntity<>(shoeOptional, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createShoe(@RequestBody ShoeRequest shoeRequest) {
        try {
            return new ResponseEntity<>(shoeService.save(shoeRequest).getId(), HttpStatus.CREATED);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoeResponse> updateShoe(@PathVariable String id, @RequestBody ShoeRequest shoeRequest) {
        try {
            return new ResponseEntity<>(shoeService.update(id, shoeRequest), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoe(@PathVariable String id) {
        try {
            shoeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
