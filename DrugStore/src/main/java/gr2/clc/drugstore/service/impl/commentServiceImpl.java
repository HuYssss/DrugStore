package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.entity.comment;
import gr2.clc.drugstore.repository.commentRepository;
import gr2.clc.drugstore.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class commentServiceImpl implements commentService {
    @Autowired
    private commentRepository repo;

    @Override
    public void saveOrUpdate(comment comment) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Optional<comment> findCommentByMedicine(String id) {
        return Optional.empty();
    }
}
