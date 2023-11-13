package gr2.clc.drugstore.service;

public interface idHandleService {
    public String getNewId(String id, String collectionName);
    public void deleteID(String id, String collectionName, String unusedId);
}
