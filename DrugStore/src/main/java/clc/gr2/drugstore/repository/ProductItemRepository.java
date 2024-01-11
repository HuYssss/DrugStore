package clc.gr2.drugstore.repository;

import clc.gr2.drugstore.entity.ProductItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductItemRepository extends MongoRepository<ProductItem, ObjectId> {
}
