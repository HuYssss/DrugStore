package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.service.idHandleService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.*;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

@Service
public class idHandleServiceImpl implements idHandleService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String getNewId(String id, String collectionName) {
        Document result = getDocument(id, collectionName);

        if (result != null) {
            List<String> listId = result.getList("unusedId", String.class);
            String tmp = result.getString("maxId");
            if (listId.isEmpty())
            {
                updateDocument(id, collectionName, increaseID(tmp), listId);
                return tmp;
            }
            else
            {
                String newID = listId.get(0);
                listId.remove(0);
                updateDocument(id, collectionName, tmp, listId);
                return newID;
            }

        }
        return null;
    }

    @Override
    public void deleteID(String id, String collectionName, String unusedId) {
        Document result = getDocument(id, collectionName);
        String maxId = result.getString("maxId");
        List<String> list = result.getList("unusedId", String.class);
        if (increaseID(unusedId).equals(maxId)) {
            updateDocument(id, collectionName, unusedId, list);
        }
        else {
            if (list == null)
                list = new ArrayList<>();
            list.add(unusedId);
            updateDocument(id, collectionName, maxId, list);
        }
    }

    private String increaseID(String maxId) {
        int tmp = Integer.parseInt(maxId.replaceAll("[^0-9]", "")) + 1;
        String toStr;
        if (tmp < 10)
            toStr = "0" + tmp;
        else
            toStr = String.valueOf(tmp);

        String output = maxId.replaceAll("[0-9]", "") + toStr;

        return output;
    }

    private Document getDocument(String id, String collection) {
        if (id == null)     return null;

        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id))
                , Document.class, collection);
    }

    private void updateDocument(String id, String collection, String tmp, List<String> list) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("maxId", tmp).set("unusedId", list);
        mongoTemplate.updateFirst(query, update, collection);
    }
}
