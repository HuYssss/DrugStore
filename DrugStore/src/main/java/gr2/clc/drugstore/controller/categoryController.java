package gr2.clc.drugstore.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gr2.clc.drugstore.entity.category;
import gr2.clc.drugstore.service.categoryService;
import gr2.clc.drugstore.helper.message;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class categoryController {
    @Autowired
    private categoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "userCookie");

        if (cookie != null) {
            List<category> list = (List<category>) categoryService.getAll();
            if (list != null) {
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(401).body(message.noLogin());
    }

    @GetMapping("/{id}")
    public Optional<category> getById(@PathVariable("id")String id, HttpServletRequest request) {
        if (checkValidUser(request))
            return categoryService.findById(id);
        else
            return Optional.empty();
    }

    @PostMapping("/save")
    public String save(@RequestBody category category, HttpServletRequest request) {
        if (checkValidUser(request))
        {
            categoryService.saveOrUpdate(category);
            return message.saveMessage(category.getId());
        }
        else
            return message.noLogin();
    }

    @PutMapping("/edit/{id}")
    public String update(@RequestBody category category,
                        @PathVariable(name = "id")String id,
                         HttpServletRequest request) {
        if (checkValidUser(request))
        {
            category.setId(id);
            categoryService.saveOrUpdate(category);
            return message.editMessage(id);
        }
        else
            return message.noLogin();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")String id, HttpServletRequest request) {
        if (checkValidUser(request))
        {
            categoryService.delete(id);
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
