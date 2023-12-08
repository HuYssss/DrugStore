package clc.gr2.drugstore.util;

import clc.gr2.drugstore.DTO.RequestDTO.AddressRequestDTO;
import clc.gr2.drugstore.DTO.RequestDTO.UserRequestDTO;
import clc.gr2.drugstore.entity.Cart;
import clc.gr2.drugstore.entity.Role;
import clc.gr2.drugstore.entity.User;
import clc.gr2.drugstore.exception.InvalidValueException;
import clc.gr2.drugstore.exception.NoParamException;
import clc.gr2.drugstore.repository.CartRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Validation {
    @Autowired
    private CartRepository cartRepository;
    public static User validateSaveUser(UserRequestDTO userRequestDTO) throws InvalidValueException, NoParamException, ParseException {
        validate(userRequestDTO);

        User parsedUser = new User();
        parsedUser.setEmail(userRequestDTO.getEmail());
        parsedUser.setPhone(userRequestDTO.getPhone());
        parsedUser.setUsername(userRequestDTO.getUsername());
        parsedUser.setPassword(userRequestDTO.getPassword());
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId(new ObjectId("656f5ba6259e2ddaab82de79"));
        roles.add(role);
        parsedUser.setRoles(roles);
        return parsedUser;
    }

    public static void validate(UserRequestDTO userRequestDTO)
            throws InvalidValueException, NoParamException, ParseException{
        if (ObjectUtils.isEmpty(userRequestDTO))
            throw new NoParamException();
        if (ObjectUtils.isEmpty(userRequestDTO.getEmail()) || ObjectUtils.isEmpty(userRequestDTO.getPhone()) ||
                ObjectUtils.isEmpty(userRequestDTO.getUsername()) || ObjectUtils.isEmpty(userRequestDTO.getPassword()))
            throw new NoParamException();
        if (!isValidEmail(userRequestDTO.getEmail()) || userRequestDTO.getPhone().length() != 10)
            throw new InvalidValueException();
    }

    public static boolean validateSaveAddress(AddressRequestDTO addressRequestDTO)
            throws NoParamException {
        if (ObjectUtils.isEmpty(addressRequestDTO) || ObjectUtils.isEmpty(addressRequestDTO.getAddress())
                || ObjectUtils.isEmpty(addressRequestDTO.getCity()) || ObjectUtils.isEmpty(addressRequestDTO.getCountry()))
            throw new NoParamException();

        return true;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
