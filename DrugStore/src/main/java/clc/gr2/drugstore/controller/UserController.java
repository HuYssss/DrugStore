package clc.gr2.drugstore.controller;

import clc.gr2.drugstore.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    private ResponseEntity<?> getAllUser() {
        return this.userService.getAllUser();
    }
}
