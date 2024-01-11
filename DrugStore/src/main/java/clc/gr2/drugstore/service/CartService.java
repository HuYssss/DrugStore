package clc.gr2.drugstore.service;

import clc.gr2.drugstore.DTO.RequestDTO.ProductItemRequest;
import clc.gr2.drugstore.entity.ProductItem;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> findCartByUser(ObjectId userId);

    ResponseEntity<?> createProductItems(ProductItemRequest item, ObjectId userId);

    ResponseEntity<?> updateProductItem(ProductItem item);

    ResponseEntity<?> deleteProductItem(ObjectId itemId, ObjectId userId);
}
