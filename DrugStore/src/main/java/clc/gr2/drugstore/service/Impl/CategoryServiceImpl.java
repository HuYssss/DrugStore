package clc.gr2.drugstore.service.Impl;

import clc.gr2.drugstore.DTO.RequestDTO.CategoryRequestDTO;
import clc.gr2.drugstore.base.ServiceBase;
import clc.gr2.drugstore.constants.ResponseCode;
import clc.gr2.drugstore.entity.Category;
import clc.gr2.drugstore.entity.Product;
import clc.gr2.drugstore.repository.CategoryRepository;
import clc.gr2.drugstore.repository.ProductRepository;
import clc.gr2.drugstore.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends ServiceBase implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<?> createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category newCate = new Category(categoryRequestDTO);
        this.categoryRepository.save(newCate);

        Optional<Category> existingCate = this.categoryRepository.findById(newCate.getId());
        if (existingCate.isPresent())
            return success(existingCate.get());
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> updateCategory(Category category) {
        Optional<Category> existingCategory = this.categoryRepository.findById(category.getId());
        if (existingCategory.isPresent())
        {
            existingCategory.get().setCategoryName(category.getCategoryName());
            existingCategory.get().setProducts(category.getProducts());

            this.categoryRepository.save(existingCategory.get());
            return success(category);
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());

    }

    @Override
    public ResponseEntity<?> delete(ObjectId categoryId) {
        Optional<Category> existingCate = this.categoryRepository.findById(categoryId);
        if (existingCate.isPresent())
        {
            this.categoryRepository.deleteById(categoryId);
            return success(categoryId);
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> getProductInCategory(ObjectId categoryId) {
        Optional<Category> existingCate = this.categoryRepository.findById(categoryId);
        List<Product> products = new ArrayList<>();
        if (existingCate.isPresent())
        {
            if (existingCate.get().getProducts().isEmpty())
            {
                return success(products);
            }
            else {
                for (ObjectId id : existingCate.get().getProducts()) {
                    products.add(findProductInProductItem(id));
                }
                return success(products);
            }
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> setProductToCategory(ObjectId categoryId, ObjectId productId) {
        Optional<Category> category = this.categoryRepository.findById(categoryId);
        Optional<Product> product = this.productRepository.findById(productId);

        if (category.isPresent() && product.isPresent())
        {
            List<ObjectId> listProducts = category.get().getProducts();
            listProducts.add(productId);
            category.get().setProducts(listProducts);
            this.categoryRepository.save(category.get());

            return success(category);
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }
}
