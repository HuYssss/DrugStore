package gr2.clc.drugstore.service;

import gr2.clc.drugstore.entity.category;
import gr2.clc.drugstore.entity.categoryEle;

import java.util.Optional;

public interface categoryService {
    public Iterable<category> getAll();
    public String saveOrUpdate(category category);
    public String delete(String id);
    public Optional<category> findById(String id);

}
