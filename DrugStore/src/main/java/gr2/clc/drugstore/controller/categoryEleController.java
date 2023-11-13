package gr2.clc.drugstore.controller;

import gr2.clc.drugstore.entity.categoryEle;
import gr2.clc.drugstore.service.categoryEleService;
import gr2.clc.drugstore.tool.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categoryEle")
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
    public String save(@RequestBody categoryEle categoryEle) {
        categoryEleService.saveOrUpdate(categoryEle);
        return message.saveMessage(categoryEle.getId());
    }

    @PutMapping("/edit/{id}")
    public String update(@RequestBody categoryEle categoryEle,
                        @PathVariable(name = "id")String id) {
        categoryEle.setId(id);
        categoryEleService.saveOrUpdate(categoryEle);
        return message.editMessage(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")String id) {
        categoryEleService.delete(id);
        return message.deleteMessage(id);
    }
}
