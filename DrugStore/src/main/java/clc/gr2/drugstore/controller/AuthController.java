package clc.gr2.drugstore.controller;

import clc.gr2.drugstore.DTO.RequestDTO.AuthenticationRequestDTO;
import clc.gr2.drugstore.DTO.RequestDTO.UserRequestDTO;
import clc.gr2.drugstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO userRequestDTO) {
        return this.userService.register(userRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        return this.userService.login(authenticationRequestDTO);
    }
}
