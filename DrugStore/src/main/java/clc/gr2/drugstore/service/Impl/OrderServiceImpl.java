package clc.gr2.drugstore.service.Impl;

import clc.gr2.drugstore.DTO.RequestDTO.OrderRequest;
import clc.gr2.drugstore.DTO.ResponseDTO.OrderResponseDTO;
import clc.gr2.drugstore.DTO.ResponseDTO.ProductItemResponse;
import clc.gr2.drugstore.base.ServiceBase;
import clc.gr2.drugstore.constants.ResponseCode;
import clc.gr2.drugstore.entity.Cart;
import clc.gr2.drugstore.entity.Order;
import clc.gr2.drugstore.entity.User;
import clc.gr2.drugstore.repository.CartRepository;
import clc.gr2.drugstore.repository.OrderRepository;
import clc.gr2.drugstore.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl extends ServiceBase implements OrderService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> findOrdersByUser(ObjectId userId) {
        Optional<User> user = findUserById(userId);
        if (user.isPresent())
        {
            List<Order> orderList = findAllOrder();
            List<OrderResponseDTO> orderRespList = new ArrayList<>();
            for (Order o : orderList) {
                if (o.getUser().equals(userId))
                {
                    OrderResponseDTO orderResp = new OrderResponseDTO(o);

                    orderResp.setProductItems(findProductItemInCart(o.getProductItems()));

                    orderRespList.add(orderResp);
                }
            }
            return success(orderRespList);
        }
        else
            return error(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.COURSE_NOT_FOUND.getMessage());
    }

    @Override
    public ResponseEntity<?> createOrder(OrderRequest order, ObjectId userId) {
        Optional<User> user = findUserById(userId);
        if (user.isPresent())
        {
            Order newOrder = new Order();

            newOrder.setId(new ObjectId());
            newOrder.setOrderDate(new Date());
            newOrder.setProductItems(order.getProductItem());
            newOrder.setTotalPrice(calculatorPrice(order.getProductItem()));
            newOrder.setUser(userId);

            this.orderRepository.save(newOrder);

            handleCreateProduct(order.getProductItem(), userId);

            return success(newOrder);
        }
        else
            return error(ResponseCode.USER_NOT_FOUND.getCode(), ResponseCode.USER_NOT_FOUND.getMessage());
    }

    private void handleCreateProduct(List<ObjectId> productItem, ObjectId userId) {
        List<Cart> cartList = this.cartRepository.findAll();
        for (Cart c : cartList) {
            if (c.getUserId().equals(userId))
            {
                c.getProductItems().removeAll(productItem);
                this.cartRepository.save(c);
            }
        }
    }

    private double calculatorPrice(List<ObjectId> productItem) {
        List<ProductItemResponse> product = findProductItemInCart(productItem);
        double result = 0.0;
        for (ProductItemResponse p : product) {
            result += p.getProduct().getPrice() * p.getQuantity();
        }
        return result;
    }


    @Override
    public ResponseEntity<?> deleteOrder(ObjectId orderId) {
        Optional<Order> existingOrder = this.orderRepository.findById(orderId);
        if (existingOrder.isPresent())
        {
            this.orderRepository.deleteById(orderId);
            return success(orderId);
        }
        else
            return error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
    }
}
