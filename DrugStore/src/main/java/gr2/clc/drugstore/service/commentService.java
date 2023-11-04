package gr2.clc.drugstore.service;

import gr2.clc.drugstore.entity.comment;

import java.util.Optional;

public interface commentService {
    public void saveOrUpdate(comment comment);
    public void delete(String id);
    public Optional<comment> findCommentByMedicine(String id);
}
