package pl.edu.wat.shoeshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.shoeshop.dto.ShoeRequest;
import pl.edu.wat.shoeshop.dto.ShoeResponse;
import pl.edu.wat.shoeshop.dto.OwnerResponse;
import pl.edu.wat.shoeshop.entity.Shoe;
import pl.edu.wat.shoeshop.entity.Owner;
import pl.edu.wat.shoeshop.exception.EntityNotFound;
import pl.edu.wat.shoeshop.repository.ShoeRepository;
import pl.edu.wat.shoeshop.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {
    private final ShoeRepository shoeRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public ShoeService(ShoeRepository shoeRepository, OwnerRepository ownerRepository) {
        this.shoeRepository = shoeRepository;
        this.ownerRepository = ownerRepository;
    }

    public ShoeResponse getShoeById(String id) throws EntityNotFound {
        Shoe shoe = shoeRepository.findById(id).orElseThrow(EntityNotFound::new);

        Owner owner = ownerRepository.findById(shoe.getOwnerId()).orElseThrow(EntityNotFound::new);

        return new ShoeResponse(
                shoe.getId(),
                shoe.getName(),
                new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname()));
    }

    public ShoeResponse save(ShoeRequest shoeRequest) throws EntityNotFound {
        Shoe shoe = new Shoe();
        shoe.setName(shoeRequest.getName());

        Owner owner = ownerRepository.findById(shoeRequest.getOwnerId())
                .orElseThrow(EntityNotFound::new);

        shoe.setOwnerId(owner.getId());
        shoe = shoeRepository.save(
                shoe
        );
        return new ShoeResponse(
                shoe.getId(),
                shoe.getName(),
                new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname()));
    }

    public List<ShoeResponse> getAll() {

        return shoeRepository.findAll()
                .stream()
                .map(this::toShoeResponse)
                .flatMap(Optional::stream)
                .toList();
    }

    private Optional<ShoeResponse> toShoeResponse(Shoe shoe) {
        try {
            Object EntityNotFoundException;
            Owner owner = ownerRepository.findById(shoe.getOwnerId()).orElseThrow(EntityNotFound::new);
            return Optional.of(
                    new ShoeResponse(shoe.getId(), shoe.getName(), new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname()))
            );
        } catch (EntityNotFound e) {
            return Optional.empty();
        }
    }

    public ShoeResponse update(String id, ShoeRequest shoeRequest) throws EntityNotFound {
        Optional<Shoe> shoeOptional = shoeRepository.findById(id);
        if (shoeOptional.isEmpty()) {
            throw new EntityNotFound();
        }
        Shoe shoe = shoeOptional.get();
        shoe.setName(shoeRequest.getName());

        Owner owner = ownerRepository.findById(shoeRequest.getOwnerId())
                .orElseThrow(EntityNotFound::new);

        shoe.setOwnerId(owner.getId());
        shoe = shoeRepository.save(shoe);
        return new ShoeResponse(shoe.getId(),shoe.getName(),new OwnerResponse(owner.getId(),owner.getName(),owner.getSurname()));
    }

    public void delete(String id) throws EntityNotFound {
        if (!shoeRepository.existsById(id)) {
            throw new EntityNotFound();
        }
        shoeRepository.deleteById(id);
    }
}

