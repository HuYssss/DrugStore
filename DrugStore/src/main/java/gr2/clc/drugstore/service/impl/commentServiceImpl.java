package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.entity.comment;
import gr2.clc.drugstore.repository.commentRepository;
import gr2.clc.drugstore.service.commentService;
import gr2.clc.drugstore.service.idHandleService;
import gr2.clc.drugstore.tool.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class commentServiceImpl implements commentService {
    @Autowired
    private commentRepository repo;

    @Autowired
    private idHandleService idService;

    @Override
    public String saveOrUpdate(comment comment) {
        if (comment != null)
        {
            if (comment.getId() == null)
                comment.setId(idService.getNewId("CA00", "category"));
            repo.save(comment);
            return message.saveMessage(comment.getId());
        }
        else
            return message.saveMessageNull();
    }

    @Override
    public String delete(String id) {
        if (StringPatternCheck(id))
        {
            idService.deleteID("CM00", "comment", id);
            repo.deleteById(id);
            return message.deleteMessage(id);
        }
        else
            return message.noParamMessage();
    }

    @Override
    public List<comment> findCommentByMedicine(String id) {
        List<comment> list = repo.findAll();
        List<comment> result = new ArrayList<>();
        for (comment c : list) {
            if (c.getMedicineId().equals(id))
                result.add(c);
        }
        return result;
    }

    private static boolean StringPatternCheck(String input) {
        String regexPattern = "^CM\\d+$";

        Pattern pattern = Pattern.compile(regexPattern);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
