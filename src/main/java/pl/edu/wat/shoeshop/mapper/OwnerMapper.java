package pl.edu.wat.shoeshop.mapper;

import org.springframework.stereotype.Service;
import pl.edu.wat.shoeshop.dto.OwnerRequest;
import pl.edu.wat.shoeshop.dto.OwnerResponse;
import pl.edu.wat.shoeshop.entity.Owner;

@Service
public class OwnerMapper {

    public Owner map(OwnerRequest ownerRequest) {
        Owner owner = new Owner();
        owner.setName(ownerRequest.getName());
        owner.setSurname(ownerRequest.getSurname());
        owner.setPseudonym(ownerRequest.getPseudonym());
        fillOwnerRequest(owner, ownerRequest);
        return owner;
    }

    private void fillOwnerRequest(Owner owner, OwnerRequest ownerRequest) {
    }

    public OwnerResponse map(Owner owner) {
        OwnerResponse ownerResponse = new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname(), owner.getPseudonym());
        fillOwner(ownerResponse, owner);
        return ownerResponse;
    }

    private void fillOwner(OwnerResponse ownerResponse, Owner owner) {
        //
        // empty for byte buddy
    }
}
