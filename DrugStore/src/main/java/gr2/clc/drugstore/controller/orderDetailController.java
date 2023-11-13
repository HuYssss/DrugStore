package gr2.clc.drugstore.controller;

import gr2.clc.drugstore.entity.orderDetail;
import gr2.clc.drugstore.service.orderDetailService;
import gr2.clc.drugstore.tool.message;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String save(@RequestBody orderDetail orderDetail) {
        orderDetailService.saveOrUpdate(orderDetail);
        return message.saveMessage(orderDetail.getId());
    }

    @PutMapping("/edit/{id}")
    public String update(@RequestBody orderDetail orderDetail,
                       @PathVariable(name = "id")String id) {
        orderDetail.setId(id);
        orderDetailService.saveOrUpdate(orderDetail);
        return message.editMessage(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")String id) {
        orderDetailService.delete(id);
        return message.deleteMessage(id);
    }
}
