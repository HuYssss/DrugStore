package clc.gr2.drugstore.service;

import clc.gr2.drugstore.DTO.RequestDTO.OrderRequest;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> findOrdersByUser(ObjectId userId);

    ResponseEntity<?> createOrder(OrderRequest order, ObjectId userId);

    ResponseEntity<?> deleteOrder(ObjectId orderId);
}
