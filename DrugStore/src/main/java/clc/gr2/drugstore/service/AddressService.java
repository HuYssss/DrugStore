package clc.gr2.drugstore.service;

import clc.gr2.drugstore.DTO.RequestDTO.AddressRequestDTO;
import clc.gr2.drugstore.entity.Address;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    ResponseEntity<?> findAddressByUser(ObjectId userId);
    ResponseEntity<?> createAddress(AddressRequestDTO addressRequestDTO, ObjectId userId);
    ResponseEntity<?> updateAddress(Address address);
    ResponseEntity<?> deleteAddress(ObjectId addressId);
}
