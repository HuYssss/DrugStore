package gr2.clc.drugstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gr2.clc.drugstore.entity.category;
import gr2.clc.drugstore.service.categoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class categoryController {
    @Autowired
    private categoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<category> list = (List<category>) categoryService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public Optional<category> getById(@PathVariable("id")String id) {
        return categoryService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody category category) {
        categoryService.saveOrUpdate(category);
    }

    @PutMapping("/edit/{id}")
    public void update(@RequestBody category category,
                        @PathVariable(name = "id")String id) {
        category.setId(id);
        categoryService.saveOrUpdate(category);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id")String id) {
        categoryService.delete(id);
    }
}
