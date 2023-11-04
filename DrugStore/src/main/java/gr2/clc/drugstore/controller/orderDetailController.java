package gr2.clc.drugstore.controller;

import gr2.clc.drugstore.entity.orderDetail;
import gr2.clc.drugstore.service.orderDetailService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderDetail")
public class orderDetailController {
    @Autowired
    private orderDetailService orderDetailService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<orderDetail> list = (List<orderDetail>) orderDetailService.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public void save(@RequestBody orderDetail orderDetail) {
        orderDetailService.saveOrUpdate(orderDetail);
    }

    @PutMapping("/edit/{id}")
    public void update(@RequestBody orderDetail orderDetail,
                       @PathVariable(name = "id")String id) {
        orderDetail.setId(id);
        orderDetailService.saveOrUpdate(orderDetail);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id")String id) {
        orderDetailService.delete(id);
    }
}
