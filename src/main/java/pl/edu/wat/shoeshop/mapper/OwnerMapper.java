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
        owner.setPseydonym(ownerRequest.getPseydonym());
        fillOwnerRequest(owner, ownerRequest);
        return owner;
    }

    private void fillOwnerRequest(Owner owner, OwnerRequest ownerRequest) {
//        owner.setRank(ownerRequest.getRank());
        // empty for byte buddy
    }

    public OwnerResponse map(Owner owner) {
        OwnerResponse ownerResponse = new OwnerResponse(owner.getId(), owner.getName(), owner.getSurname());
        fillOwner(ownerResponse, owner);
        return ownerResponse;
    }

    private void fillOwner(OwnerResponse ownerResponse, Owner owner) {
        //ownerResponse.setRank(owner.getRank());
        // empty for byte buddy
    }


}
