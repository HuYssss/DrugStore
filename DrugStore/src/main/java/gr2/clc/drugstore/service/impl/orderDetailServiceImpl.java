package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.service.idHandleService;
import gr2.clc.drugstore.helper.message;
import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.orderDetail;
import gr2.clc.drugstore.repository.orderDetailRepository;
import gr2.clc.drugstore.service.orderDetailService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class orderDetailServiceImpl implements orderDetailService {
    @Autowired
    private orderDetailRepository repo;
    @Autowired
    private idHandleService idService;

    @Override
    public Iterable<orderDetail> getAll() {
        return this.repo.findAll();
    }

    @Override
    public String saveOrUpdate(orderDetail orderDetail) {
        if (orderDetail != null)
        {
            if (orderDetail.getId() == null)
                orderDetail.setId(idService.getNewId("OD00", "orderDetail"));
            repo.save(orderDetail);
            return message.saveMessage(orderDetail.getId());
        }
        else
            return message.saveMessageNull();
    }

    @Override
    public String delete(String id) {
        if (StringPatternCheck(id))
        {
            idService.deleteID("OD00", "orderDetail", id);
            repo.deleteById(id);
            return message.deleteMessage(id);
        }
        else
            return message.noParamMessage();
    }

    @Override
    public Optional<orderDetail> findById(String id) {
        if (StringPatternCheck(id))
            return repo.findById(id);
        else
            return Optional.empty();
    }

    private static boolean StringPatternCheck(String input) {
        String regexPattern = "^OD\\d+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
