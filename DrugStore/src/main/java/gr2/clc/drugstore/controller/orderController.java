package gr2.clc.drugstore.controller;


import gr2.clc.drugstore.entity.order;
import gr2.clc.drugstore.service.orderService;
import gr2.clc.drugstore.helper.message;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class orderController {
    @Autowired
    private orderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        if (checkValidUser(request))
        {
            List<order> list = (List<order>) orderService.getAll();
            return ResponseEntity.ok(list);
        }
        else
            return null;

    }

    @PostMapping("/save")
    public String save(@RequestBody order order, HttpServletRequest request) {
        if (checkValidUser(request))
        {
            orderService.saveOrUpdate(order);
            return message.saveMessage(order.getId());
        }
        else
            return null;

    }

    @PutMapping("/edit/{id}")
    public String update(@RequestBody order order,
                       @PathVariable(name = "id")String id,
                         HttpServletRequest request) {
        if (checkValidUser(request))
        {
            order.setId(id);
            orderService.saveOrUpdate(order);
            return message.saveMessage(id);
        }
        else
            return message.noLogin();

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")String id, HttpServletRequest request) {
        if (checkValidUser(request))
        {
            orderService.delete(id);
            return message.deleteMessage(id);
        }
        else
            return message.noLogin();
    }

    private static boolean checkValidUser(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "userCookie");
        return cookie != null;
    }
}
