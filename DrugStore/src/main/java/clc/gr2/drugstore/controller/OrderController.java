package clc.gr2.drugstore.controller;

import clc.gr2.drugstore.DTO.RequestDTO.OrderRequest;
import clc.gr2.drugstore.service.OrderService;
import clc.gr2.drugstore.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUserOrder(Principal principal) {
        return this.orderService.findOrdersByUser(
                this.userService.findUserByUsername(principal.getName())
                        .getId()
        );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest, Principal principal) {
        return  this.orderService.createOrder(orderRequest,
                this.userService.findUserByUsername(principal.getName()).getId()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") ObjectId orderId) {
        return this.orderService.deleteOrder(orderId);
    }
}
