package clc.gr2.drugstore.controller;

import clc.gr2.drugstore.DTO.RequestDTO.ProductRequest;
import clc.gr2.drugstore.entity.Product;
import clc.gr2.drugstore.repository.ProductRepository;
import clc.gr2.drugstore.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        return this.productService.getAllProduct();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewProduct(@RequestBody ProductRequest productRequest) {
        return this.productService.createProduct(productRequest);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest) {
        Product updateProduct = new Product();
        updateProduct.setId(new ObjectId());
        updateProduct.setName(productRequest.getName());
        updateProduct.setImg(productRequest.getImg());
        updateProduct.setManufacturer(productRequest.getManufacturer());
        updateProduct.setDescription(productRequest.getDescription());
        updateProduct.setPrice(productRequest.getPrice());

        return this.productService.updateProduct(updateProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") ObjectId productId) {
        return this.productService.deleteProduct(productId);
    }
}
