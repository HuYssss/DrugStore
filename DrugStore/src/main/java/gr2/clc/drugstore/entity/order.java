package gr2.clc.drugstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class order {
    @Id
    private String id;
    private String orderDate;
    private String customerId;
    private List<String> orderDetail;
    private double totalAmount;
}
