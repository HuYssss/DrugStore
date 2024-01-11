package clc.gr2.drugstore.service.Impl;

import clc.gr2.drugstore.DTO.RequestDTO.ProductItemRequest;
import clc.gr2.drugstore.DTO.ResponseDTO.CartResponseDTO;
import clc.gr2.drugstore.DTO.ResponseDTO.ProductItemResponse;
import clc.gr2.drugstore.base.ServiceBase;
import clc.gr2.drugstore.constants.ResponseCode;
import clc.gr2.drugstore.entity.Cart;
import clc.gr2.drugstore.entity.ProductItem;
import clc.gr2.drugstore.entity.User;
import clc.gr2.drugstore.repository.CartRepository;
import clc.gr2.drugstore.repository.ProductItemRepository;
import clc.gr2.drugstore.service.CartService;
import com.mongodb.MongoException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl extends ServiceBase implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Override
    public ResponseEntity<?> findCartByUser(ObjectId userId) throws MongoException {
        Optional<User> user = findUserById(userId);
        if (user.isPresent()) {
            List<Cart> carts = this.cartRepository.findAll();
            for (Cart c : carts) {
                if (c.getUserId().equals(userId)) {
                    CartResponseDTO cartResp = new CartResponseDTO();
                    List<ProductItemResponse> productItems = findProductItemInCart(c.getProductItems());
                    cartResp.setProductItems(productItems);
                    return success(cartResp);
                }
            }
        }
        return error(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.USER_NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> createProductItems(ProductItemRequest item, ObjectId userId) {
        Optional<User> user = findUserById(userId);
        if (user.isPresent()) {
            ProductItem newItem = new ProductItem();
            newItem.setProductId(new ObjectId());
            newItem.setProductId(item.getProduct());
            newItem.setQuantity(item.getQuantity());

            this.productItemRepository.save(newItem);
            List<Cart> carts = this.cartRepository.findAll();
            for (Cart c : carts) {
                if (c.getUserId().equals(userId)) {
                    c.getProductItems().add(newItem.getId());
                    this.cartRepository.save(c);

                    return success(item);
                }
            }
        }
        return error(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.USER_NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> updateProductItem(ProductItem item) {
        Optional<ProductItem> currentItem = this.productItemRepository.findById(item.getId());
        if (currentItem.isPresent())
        {
            currentItem.get().setProductId(item.getProductId());
            currentItem.get().setQuantity(item.getQuantity());

            this.productItemRepository.save(currentItem.get());

            return success(currentItem);
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> deleteProductItem(ObjectId itemId, ObjectId userId) {
        Optional<User> user = findUserById(userId);
        if (user.isPresent()) {
            List<Cart> carts = this.cartRepository.findAll();
            for (Cart c : carts) {
                if (c.getUserId().equals(userId)) {
                    c.getProductItems().remove(itemId);
                    this.cartRepository.save(c);
                    this.productItemRepository.deleteById(itemId);
                    return success(itemId);
                }
            }
        }
        return error(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.USER_NOT_FOUND.getMessage());
    }


}
