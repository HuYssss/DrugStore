package gr2.clc.drugstore.controller;

import gr2.clc.drugstore.entity.medicine;
import gr2.clc.drugstore.service.medicineService;
import gr2.clc.drugstore.helper.message;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/medicine")
public class medicineController {
    @Autowired
    private medicineService medicineService;

    @GetMapping("")
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        List<medicine> list = (List<medicine>) medicineService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public Optional<medicine> getById(@PathVariable("id")String id, HttpServletRequest request) {
        if (checkValidUser(request))
        {
            return medicineService.findById(id);
        }
        else
            return Optional.empty();
    }

    @PostMapping("/save")
    public String save(@RequestBody medicine medicine, HttpServletRequest request) {
        if (checkValidUser(request))
        {
            medicineService.saveOrUpdate(medicine);
            return message.saveMessage(medicine.getId());
        }
        else
            return message.noLogin();

    }

    @PutMapping("/edit/{id}")
    public String update(@RequestBody medicine medicine,
                       @PathVariable(name = "id")String id,
                         HttpServletRequest request) {
        if (checkValidUser(request))
        {
            medicine.setId(id);
            medicineService.saveOrUpdate(medicine);
            return message.editMessage(id);
        }
        else
            return message.noLogin();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")String id,
                         HttpServletRequest request) {
        if (checkValidUser(request))
        {
            medicineService.delete(id);
            return message.deleteMessage(id);
        }
        else
            return message.noLogin();
    }

    @GetMapping("/find/{name}")
    public Iterable<medicine> getByName(@PathVariable(name = "name") String name,
                                        HttpServletRequest request) {
        if (checkValidUser(request))
            return medicineService.getByName(name);
        else
            return null;
    }

    private static boolean checkValidUser(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "userCookie");
        return cookie != null;
    }
}
