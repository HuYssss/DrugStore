package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.service.idHandleService;
import gr2.clc.drugstore.helper.message;
import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.medicine;
import gr2.clc.drugstore.repository.medicineRepository;
import gr2.clc.drugstore.service.medicineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class medicineServiceImpl implements medicineService {
    @Autowired
    private medicineRepository repo;

    @Autowired
    private idHandleService idService;

    @Override
    public Iterable<medicine> getAll() {
        return this.repo.findAll();
    }

    @Override
    public String saveOrUpdate(medicine medicine) {
        if (medicine != null)
        {
            if (medicine.getId() == null)
                medicine.setId(idService.getNewId("M00", "medicine"));
            repo.save(medicine);
            return message.saveMessage(medicine.getId());
        }
        else
            return message.saveMessageNull();
    }

    @Override
    public String delete(String id) {
        if (StringPatternCheck(id))
        {
            idService.deleteID("M00", "medicine", id);
            repo.deleteById(id);
            return message.deleteMessage(id);
        }
        else
            return message.noParamMessage();
    }

    @Override
    public Optional<medicine> findById(String id) {
        if (StringPatternCheck(id))
            return repo.findById(id);
        else
            return Optional.empty();
    }

    @Override
    public Iterable<medicine> getByCateID(String id) {
        return repo.findAll().stream()
                .filter(m -> m.getCategory().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<medicine> getByName(String name) {
        List<medicine> result = new ArrayList<>();
        List<medicine> list = repo.findAll();
        for (medicine s : list) {
            if (s.getName().equals(name)) {
                result.add(s);
            }
        }
        return result;
    }

    private static boolean StringPatternCheck(String input) {
        String regexPattern = "^M\\d+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
