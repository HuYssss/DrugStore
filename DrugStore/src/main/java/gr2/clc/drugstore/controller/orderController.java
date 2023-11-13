package gr2.clc.drugstore.controller;


import gr2.clc.drugstore.entity.order;
import gr2.clc.drugstore.service.orderService;
import gr2.clc.drugstore.tool.message;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class orderController {
    @Autowired
    private orderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<order> list = (List<order>) orderService.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public String save(@RequestBody order order) {
        orderService.saveOrUpdate(order);
        return message.saveMessage(order.getId());
    }

    @PutMapping("/edit/{id}")
    public String update(@RequestBody order order,
                       @PathVariable(name = "id")String id) {
        order.setId(id);
        orderService.saveOrUpdate(order);
        return message.saveMessage(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")String id) {
        orderService.delete(id);
        return message.deleteMessage(id);
    }
}
