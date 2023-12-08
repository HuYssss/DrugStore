package clc.gr2.drugstore.controller;

import clc.gr2.drugstore.DTO.RequestDTO.ProductItemRequest;
import clc.gr2.drugstore.entity.ProductItem;
import clc.gr2.drugstore.service.CartService;
import clc.gr2.drugstore.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getCartUser(Principal principal) {
        return this.cartService.findCartByUser(
                this.userService.findUserByUsername(principal.getName())
                        .getId());
    }

    @PostMapping("/addProductItem")
    public ResponseEntity<?> addNewProductItem(@RequestBody ProductItemRequest productItemRequest,
                                                Principal principal) {
        return this.cartService.createProductItems(productItemRequest,
                this.userService.findUserByUsername(principal.getName())
                        .getId());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateProductItem(@RequestBody ProductItemRequest productItemRequest
            , @PathVariable(name = "id")ObjectId itemId) {
        ProductItem updateItem = new ProductItem();
        updateItem.setProductId(new ObjectId());
        updateItem.setProductId(productItemRequest.getProduct());
        updateItem.setQuantity(productItemRequest.getQuantity());
        return this.cartService.updateProductItem(updateItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductItem(@PathVariable(name = "id") ObjectId itemId, Principal principal) {
        return this.cartService.deleteProductItem(itemId,
                this.userService.findUserByUsername(principal.getName())
                        .getId());
    }
}
