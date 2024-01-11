package clc.gr2.drugstore.service;

import clc.gr2.drugstore.DTO.RequestDTO.ProductRequest;
import clc.gr2.drugstore.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> getAllProduct();
    ResponseEntity<?> createProduct(ProductRequest product);
    ResponseEntity<?> updateProduct(Product product);
    ResponseEntity<?> deleteProduct(ObjectId product);
}
