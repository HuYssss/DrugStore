package clc.gr2.drugstore.service.Impl;

import clc.gr2.drugstore.DTO.RequestDTO.AddressRequestDTO;
import clc.gr2.drugstore.base.ServiceBase;
import clc.gr2.drugstore.constants.ResponseCode;
import clc.gr2.drugstore.entity.Address;
import clc.gr2.drugstore.entity.User;
import clc.gr2.drugstore.repository.AddressRepository;
import clc.gr2.drugstore.service.AddressService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl extends ServiceBase implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public ResponseEntity<?> findAddressByUser(ObjectId userId) {
        List<Address> list = findAllAddress();
        for (Address a : list) {
            if (!a.getUser().equals(userId))
                list.remove(a);
        }
        return success(list);
    }

    @Override
    public ResponseEntity<?> createAddress(AddressRequestDTO addressRequestDTO, ObjectId userId) {
        Optional<User> user = findUserById(userId);
        if (user.isPresent())
        {
            Address currentAddress = new Address(addressRequestDTO);
            currentAddress.setUser(userId);

            this.addressRepository.save(currentAddress);

            return success(currentAddress);
        }
        else
            return error(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.USER_NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> updateAddress(Address address) {
        Optional<Address> currentAddress = this.addressRepository.findById(address.getId());
        if (currentAddress.isPresent()) {

            currentAddress.get().setAddress(address.getAddress());
            currentAddress.get().setCity(address.getCity());
            currentAddress.get().setCountry(address.getCountry());

            this.addressRepository.save(currentAddress.get());
            return success(address);
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> deleteAddress(ObjectId addressId) {
        Optional<Address> currentAddress = this.addressRepository.findById(addressId);
        if (currentAddress.isPresent())
        {
            this.addressRepository.deleteById(addressId);
            return success(addressId);
        }
        else
            return error(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.USER_NOT_FOUND.getMessage());
    }

}
