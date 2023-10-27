package gr2.clc.drugstore.controller;

import gr2.clc.drugstore.entity.medicine;
import gr2.clc.drugstore.service.medicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicine")
public class medicineController {
    @Autowired
    private medicineService medicineService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<medicine> list = (List<medicine>) medicineService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public Optional<medicine> getById(@PathVariable("id")String id) {
        return medicineService.findById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody medicine medicine) {
        medicineService.saveOrUpdate(medicine);
    }

    @PutMapping("/edit/{id}")
    public void update(@RequestBody medicine medicine,
                       @PathVariable(name = "id")String id) {
        medicine.setId(id);
        medicineService.saveOrUpdate(medicine);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id")String id) {
        medicineService.delete(id);
    }
}
