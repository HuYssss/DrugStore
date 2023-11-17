package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.entity.orderDetail;
import gr2.clc.drugstore.service.idHandleService;
import gr2.clc.drugstore.helper.message;
import org.springframework.beans.factory.annotation.Autowired;
import gr2.clc.drugstore.entity.order;
import gr2.clc.drugstore.repository.orderRepository;
import gr2.clc.drugstore.service.orderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class orderServiceImpl implements orderService {
    @Autowired
    private orderRepository repo;
    @Autowired
    private idHandleService idService;

    @Override
    public Iterable<order> getAll() {
        return this.repo.findAll();
    }

    @Override
    public String saveOrUpdate(order order) {
        if (order != null)
        {
            if (order.getId() == null)
                order.setId(idService.getNewId("OR00", "order"));
            repo.save(order);
            return message.saveMessage(order.getId());
        }
        else
            return message.saveMessageNull();
    }

    @Override
    public String delete(String id) {
        if (StringPatternCheck(id))
        {
            idService.deleteID("OR00", "order", id);
            repo.deleteById(id);
            return message.deleteMessage(id);
        }
        else
            return message.noParamMessage();
    }

    @Override
    public Optional<order> findById(String id) {
        if (StringPatternCheck(id))
            return repo.findById(id);
        else
            return Optional.empty();
    }

    @Override
    public Iterable<orderDetail> findAllOrderDetail(String id) {
        Optional<order> tmp = repo.findById(id);
        if (tmp.isPresent())
            return tmp.get().getOrderDetail();
        else
            return null;
    }

    private static boolean StringPatternCheck(String input) {
        String regexPattern = "^OR\\d+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
