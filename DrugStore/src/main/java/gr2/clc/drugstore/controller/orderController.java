package gr2.clc.drugstore.controller;


import gr2.clc.drugstore.entity.order;
import gr2.clc.drugstore.service.orderService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class orderController {
    @Autowired
    private orderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<order> list = (List<order>) orderService.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public void save(@RequestBody order order) {
        orderService.saveOrUpdate(order);
    }

    @PutMapping("/edit/{id}")
    public void update(@RequestBody order order,
                       @PathVariable(name = "id")String id) {
        order.setId(id);
        orderService.saveOrUpdate(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id")String id) {
        orderService.delete(id);
    }
}
