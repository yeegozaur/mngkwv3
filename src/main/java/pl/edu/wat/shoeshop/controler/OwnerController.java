package pl.edu.wat.shoeshop.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.shoeshop.dto.OwnerRequest;
import pl.edu.wat.shoeshop.dto.OwnerResponse;
import pl.edu.wat.shoeshop.exception.EntityNotFound;
import pl.edu.wat.shoeshop.service.OwnerService;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping()
    public ResponseEntity<List<OwnerResponse>> getAllOwner() {
        List<OwnerResponse> ownerOptional = ownerService.getAll();
        return new ResponseEntity<>(ownerOptional, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OwnerResponse> getOwnerByIdVar(@PathVariable String id) {
        Optional<OwnerResponse> ownerOptional = ownerService.getOwnerById(id);
        if (ownerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ownerOptional.get(), HttpStatus.OK);
    }

    @GetMapping("{id}/surname")
    public ResponseEntity<String> getOwnerSurnameById(@PathVariable String id) {
        Optional<OwnerResponse> ownerOptional = ownerService.getOwnerById(id);
        if (ownerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ownerOptional.get().getSurname(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createOwner(@RequestBody OwnerRequest ownerRequest) {
        return new ResponseEntity<>(ownerService.save(ownerRequest).getId(), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<OwnerResponse> updateOwner(@PathVariable String id, @RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        try {
            return new ResponseEntity<>(ownerService.update(id, name, surname), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String id) {
        try {
            ownerService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
