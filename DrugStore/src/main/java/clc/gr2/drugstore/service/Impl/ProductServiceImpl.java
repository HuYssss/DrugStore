package clc.gr2.drugstore.service.Impl;

import clc.gr2.drugstore.DTO.RequestDTO.ProductRequest;
import clc.gr2.drugstore.base.ServiceBase;
import clc.gr2.drugstore.constants.ResponseCode;
import clc.gr2.drugstore.entity.Category;
import clc.gr2.drugstore.entity.Product;
import clc.gr2.drugstore.repository.CategoryRepository;
import clc.gr2.drugstore.repository.ProductRepository;
import clc.gr2.drugstore.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends ServiceBase implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = this.productRepository.findAll();
        return success(products);
    }

    @Override
    public ResponseEntity<?> createProduct(ProductRequest product) {
        Product newProduct = new Product();

        newProduct.setId(new ObjectId());
        newProduct.setName(product.getName());
        newProduct.setImg(product.getImg());
        newProduct.setDescription(product.getDescription());
        newProduct.setManufacturer(product.getManufacturer());

        this.productRepository.save(newProduct);

        return success(newProduct);
    }

    @Override
    public ResponseEntity<?> updateProduct(Product product) {
        Optional<Product> currentProduct = this.productRepository.findById(product.getId());
        if (currentProduct.isPresent())
        {
            this.productRepository.save(product);
            return success(product);
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> deleteProduct(ObjectId productId) {
        Optional<Product> currentProduct = this.productRepository.findById(productId);
        if (currentProduct.isPresent())
        {
            if (currentProduct.get().getCategoryId() != null)
                handleDeleteProduct(currentProduct.get());
            this.productRepository.deleteById(productId);
            return success(productId);
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }

    public void handleDeleteProduct(Product product) {
        Optional<Category> category = this.categoryRepository.findById(product.getCategoryId());
        if (category.isPresent())
        {
            List<ObjectId> products = category.get().getProducts();
            products.remove(product.getCategoryId());
            category.get().setProducts(products);

            this.categoryRepository.save(category.get());
        }
    }
}
