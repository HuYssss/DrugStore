package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.service.categoryService;
import gr2.clc.drugstore.service.idHandleService;
import gr2.clc.drugstore.service.medicineService;
import gr2.clc.drugstore.tool.message;
import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.categoryEle;
import gr2.clc.drugstore.repository.categoryEleRepository;
import gr2.clc.drugstore.service.categoryEleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class categoryEleServiceImpl implements categoryEleService {
    private categoryEleRepository repo;
    private idHandleService idService;
    private categoryService repoCate;
    private medicineService repoMedicine;

    @Autowired
    public void setRepo(categoryEleRepository repo) {
        this.repo = repo;
    }

    @Autowired
    public void setIdService(idHandleService idService) {
        this.idService = idService;
    }

    @Autowired
    public void setRepoCate(categoryService repoCate) {
        this.repoCate = repoCate;
    }

    @Autowired
    public void setRepoMedicine(medicineService repoMedicine) {
        this.repoMedicine = repoMedicine;
    }

    @Override
    public Iterable<categoryEle> getAll() {
        return this.repo.findAll();
    }

    @Override
    public String saveOrUpdate(categoryEle categoryEle) {
        if (categoryEle != null)
        {
            if (categoryEle.getId() == null)
                categoryEle.setId(idService.getNewId("CAE00", "categoryEle"));
            repo.save(categoryEle);
            return message.saveMessage(categoryEle.getId());
        }
        else
            return message.saveMessageNull();
    }

    @Override
    public String delete(String id) {
        if (StringPatternCheck(id))
        {
            idService.deleteID("CAE00", "categoryEle", id);
            handleDeleteCateEle(id);
            repo.deleteById(id);
            return message.deleteMessage(id);
        }
        else
            return message.noParamMessage();
    }

    @Override
    public Optional<categoryEle> findById(String id) {
        if (StringPatternCheck(id))
            return repo.findById(id);
        else
            return Optional.empty();
    }

    private static boolean StringPatternCheck(String input) {
        String regexPattern = "^CAE\\d+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    private void handleDeleteCateEle(String id) {
        repo.findById(id).ifPresent(result -> {
            String tmp = result.getCategory();
            repoCate.findById(tmp).ifPresent(results -> {
                List<String> list = results.getCateEle();
                list.remove(id);
                results.setCateEle(list);
                repoCate.saveOrUpdate(results);
            });
        });

        repoMedicine.getByCateEleID(id).forEach(s -> {
            s.setCategoryEle("CAE09");
            repoMedicine.saveOrUpdate(s);
        });
    }
}
