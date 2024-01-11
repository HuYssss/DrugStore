package clc.gr2.drugstore.service;

import clc.gr2.drugstore.DTO.RequestDTO.CategoryRequestDTO;
import clc.gr2.drugstore.entity.Category;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> createCategory(CategoryRequestDTO categoryRequestDTO);
    ResponseEntity<?> updateCategory(Category category);
    ResponseEntity<?> delete(ObjectId categoryId);
    ResponseEntity<?> getProductInCategory(ObjectId categoryId);

    ResponseEntity<?> setProductToCategory(ObjectId categoryId, ObjectId productId);
}
