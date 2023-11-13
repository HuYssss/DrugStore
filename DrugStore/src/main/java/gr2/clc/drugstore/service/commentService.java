package gr2.clc.drugstore.service;

import gr2.clc.drugstore.entity.comment;

import java.util.List;

public interface commentService {
    public String saveOrUpdate(comment comment);
    public String delete(String id);
    public List<comment> findCommentByMedicine(String id);
}
