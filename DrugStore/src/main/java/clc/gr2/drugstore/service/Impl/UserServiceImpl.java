package clc.gr2.drugstore.service.Impl;

import clc.gr2.drugstore.DTO.RequestDTO.AuthenticationRequestDTO;
import clc.gr2.drugstore.DTO.RequestDTO.UserRequestDTO;
import clc.gr2.drugstore.DTO.ResponseDTO.AuthenticationResponseDTO;
import clc.gr2.drugstore.base.ServiceBase;
import clc.gr2.drugstore.constants.ResponseCode;
import clc.gr2.drugstore.entity.Cart;
import clc.gr2.drugstore.entity.User;
import clc.gr2.drugstore.exception.InvalidValueException;
import clc.gr2.drugstore.exception.NoParamException;
import clc.gr2.drugstore.repository.CartRepository;
import clc.gr2.drugstore.repository.UserRepository;
import clc.gr2.drugstore.service.UserService;
import clc.gr2.drugstore.util.JwtUtils;
import clc.gr2.drugstore.util.Validation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceBase implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> register(UserRequestDTO userRes) {
        try {
            User saveUser = Validation.validateSaveUser(userRes);
            saveUser.setPassword(this.passwordEncoder.encode(saveUser.getPassword()));
            this.userRepository.save(saveUser);

            Cart cart = new Cart();
            cart.setUserId(saveUser.getId());
            this.cartRepository.save(cart);

            return success(saveUser);
        } catch (InvalidValueException e) {
            throw new RuntimeException(e);
        } catch (NoParamException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> login(AuthenticationRequestDTO authenticationRequestDTO) {
        if (ObjectUtils.isEmpty(authenticationRequestDTO) || ObjectUtils.isEmpty(authenticationRequestDTO.getPassword())
                || ObjectUtils.isEmpty(authenticationRequestDTO.getUsername()))
            return error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword()));

            String token = JwtUtils.generateToken(authenticationRequestDTO.getUsername());
            AuthenticationResponseDTO authResponse = new AuthenticationResponseDTO(token, "Login Successful !!!");
            return success(authResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return error(ResponseCode.INCORRECT_AUTHEN.getCode(), ResponseCode.INCORRECT_AUTHEN.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(ObjectId userId) {
        Optional<?> currentUser = this.userRepository.findById(userId);
        if (currentUser.isPresent())
        {
            this.userRepository.deleteById(userId);
            return success(userId);
        }
        else
            return error(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.USER_NOT_FOUND.getMessage());
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> currentUser = this.userRepository.findUserByUsername(username);
        return currentUser.orElse(null);
    }

    @Override
    public ResponseEntity<?> getAllUser() {
        List<User> userList = this.userRepository.findAll();
        if (!userList.isEmpty())
            return success(userList);
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }
}
