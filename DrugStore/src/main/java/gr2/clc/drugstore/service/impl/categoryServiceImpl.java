package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.service.idHandleService;
import gr2.clc.drugstore.service.medicineService;
import gr2.clc.drugstore.helper.message;
import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.category;
import gr2.clc.drugstore.repository.categoryRepository;
import gr2.clc.drugstore.service.categoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class categoryServiceImpl implements categoryService {
    @Autowired
    private categoryRepository repo;

    @Autowired
    private idHandleService idService;

    @Autowired
    private medicineService repoMedicine;

    @Override
    public Iterable<category> getAll() {
        return repo.findAll();
    }

    @Override
    public String saveOrUpdate(category category) {
        if (category != null)
        {
            if (category.getId() == null)
                category.setId(idService.getNewId("CA00", "category"));
            repo.save(category);
            return message.saveMessage(category.getId());
        }
        else
            return message.saveMessageNull();
    }

    @Override
    public String delete(String id) {
        if (StringPatternCheck(id))
        {
            idService.deleteID("CA00", "category", id);
            handleDeleteCategory(id);
            repo.deleteById(id);
            return message.deleteMessage(id);
        }
        else
            return message.noParamMessage();
    }

    @Override
    public Optional<category> findById(String id) {
        if (StringPatternCheck(id))
            return repo.findById(id);
        else
            return Optional.empty();
    }


    private static boolean StringPatternCheck(String input) {
        String regexPattern = "^CA\\d+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    private void handleDeleteCategory(String id) {
        repoMedicine.getByCateID(id).forEach(s -> {
            s.setCategory("CA09");
            s.setCategoryEle("null");
            repoMedicine.saveOrUpdate(s);
        });
    }
}
