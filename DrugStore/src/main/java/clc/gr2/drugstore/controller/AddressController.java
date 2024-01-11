package clc.gr2.drugstore.controller;

import clc.gr2.drugstore.DTO.RequestDTO.AddressRequestDTO;
import clc.gr2.drugstore.entity.Address;
import clc.gr2.drugstore.service.AddressService;
import clc.gr2.drugstore.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUserAddress(Principal principal) {
        return this.addressService.findAddressByUser(
                this.userService.findUserByUsername(principal.getName())
                        .getId());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewAddress(@RequestBody AddressRequestDTO addressRequestDTO, Principal principal) {
        return this.addressService.createAddress(addressRequestDTO,
                this.userService.findUserByUsername(
                                principal.getName()).getId()
                );
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateAddress(@RequestBody AddressRequestDTO addressRequestDTO
            , @PathVariable(name = "id") ObjectId addressId
            , Principal principal) {
        Address updateAddress = new Address(addressRequestDTO);
        updateAddress.setId(addressId);
        updateAddress.setUser(
                this.userService.findUserByUsername(
                                principal.getName()).getId()
        );
        return this.addressService.updateAddress(updateAddress);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(name = "id") ObjectId addressId) {
        return this.addressService.deleteAddress(addressId);
    }
}
