package clc.gr2.drugstore.controller;

import clc.gr2.drugstore.DTO.RequestDTO.AddProductCategory;
import clc.gr2.drugstore.DTO.RequestDTO.CategoryRequestDTO;
import clc.gr2.drugstore.entity.Category;
import clc.gr2.drugstore.repository.CategoryRepository;
import clc.gr2.drugstore.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductInCategory(@PathVariable(name = "id") ObjectId categoryId) {
        return this.categoryService.getProductInCategory(categoryId);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewCate(@RequestBody CategoryRequestDTO newCate) {
        return this.categoryService.createCategory(newCate);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProductToCategory(@RequestBody AddProductCategory newCate) {
        return this.categoryService.setProductToCategory(newCate.getCategoryId(), newCate.getProductId());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryRequestDTO categoryRequestDTO,
                                            @PathVariable(name = "id") ObjectId categoryId) {
        Category updateCate = new Category();
        updateCate.setId(categoryId);
        updateCate.setCategoryName(categoryRequestDTO.getCategoryName());
        updateCate.setProducts(categoryRequestDTO.getProducts());

        return this.categoryService.updateCategory(updateCate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") ObjectId categoryId) {
        return this.categoryService.delete(categoryId);
    }
}
