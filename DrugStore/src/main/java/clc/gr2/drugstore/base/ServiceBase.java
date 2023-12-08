package clc.gr2.drugstore.base;

import clc.gr2.drugstore.DTO.ResponseDTO.ProductItemResponse;
import clc.gr2.drugstore.entity.*;
import clc.gr2.drugstore.repository.*;
import clc.gr2.drugstore.constants.ResponseCode;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ServiceBase {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> success(Object object) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", object);
        response.put("code", ResponseCode.OK.getCode());
        response.put("message", ResponseCode.OK.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> error(int code, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    public Optional<User> findUserById(ObjectId userId) {
        return this.userRepository.findById(userId);
    }

    public List<Address> findAllAddress() {
        return this.addressRepository.findAll();
    }

    public List<Cart> findAllCart() {
        return this.cartRepository.findAll();
    }

    public List<Order> findAllOrder() {
        return this.orderRepository.findAll();
    }

    public List<Role> findAllRole() {
        return this.roleRepository.findAll();
    }

    public List<ProductItemResponse> findProductItemInCart(List<ObjectId> listProductItem) {
        List<ProductItemResponse> result = new ArrayList<>();

        for (ObjectId id : listProductItem) {
            Optional<ProductItem> item = this.productItemRepository.findById(id);
            if (item.isPresent()) {

                ProductItemResponse itemResp = new ProductItemResponse();
                itemResp.setProduct(findProductInProductItem(item.get().getProductId()));
                itemResp.setQuantity(item.get().getQuantity());

                result.add(itemResp);
            }
        }

        return result;
    }

    public Product findProductInProductItem(ObjectId productId) {
        Optional<Product> product = this.productRepository.findById(productId);
        return product.orElse(null);
    }
}
