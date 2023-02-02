package pl.edu.wat.shoeshop.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.shoeshop.dto.OwnerRequest;
import pl.edu.wat.shoeshop.dto.OwnerResponse;
import pl.edu.wat.shoeshop.entity.Owner;
import pl.edu.wat.shoeshop.exception.EntityNotFound;
import pl.edu.wat.shoeshop.mapper.OwnerMapper;
import pl.edu.wat.shoeshop.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    public Optional<OwnerResponse> getOwnerById(String id) {
        return ownerRepository.findById(id)
                .map(ownerMapper::map);
    }

    public OwnerResponse save(OwnerRequest ownerRequest) {
        Owner owner = ownerMapper.map(ownerRequest);
        owner = ownerRepository.save(
                owner
        );
        return ownerMapper.map(owner);
    }

    public List<OwnerResponse> getAll() {
        return ownerRepository.findAll()
                .stream()
                .map(ownerMapper::map)
                .toList();
    }

    public OwnerResponse update(String id, String name, String surname, String pseudonym) throws EntityNotFound {
        Owner owner = ownerRepository.findById(id).orElseThrow(EntityNotFound::new);
        if(StringUtils.isNotBlank(name)) {
            owner.setName(name);
        }

        if(StringUtils.isNotBlank(surname)) {
            owner.setSurname(surname);
        }

        if(StringUtils.isNotBlank(pseudonym)) {
            owner.setPseudonym(pseudonym);
        }

        owner = ownerRepository.save(owner);
        return ownerMapper.map(owner);
    }

    public void delete(String id) throws EntityNotFound {
        if(!ownerRepository.existsById(id)) {
            throw new EntityNotFound();
        }
        ownerRepository.deleteById(id);
    }
}
