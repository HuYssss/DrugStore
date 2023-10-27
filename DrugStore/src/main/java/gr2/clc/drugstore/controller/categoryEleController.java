package gr2.clc.drugstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gr2.clc.drugstore.entity.categoryEle;
import gr2.clc.drugstore.service.categoryEleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoryEle")
public class categoryEleController {
    @Autowired
    private categoryEleService categoryEleService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<categoryEle> list = (List<categoryEle>) categoryEleService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public Optional<categoryEle> getById(@PathVariable("id")String id) {
        return categoryEleService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody categoryEle categoryEle) {
        categoryEleService.saveOrUpdate(categoryEle);
    }

    @PutMapping("/edit/{id}")
    public void update(@RequestBody categoryEle categoryEle,
                        @PathVariable(name = "id")String id) {
        categoryEle.setId(id);
        categoryEleService.saveOrUpdate(categoryEle);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id")String id) {
        categoryEleService.delete(id);
    }
}
