package gr2.clc.drugstore.entity;

import gr2.clc.drugstore.service.idHandleService;
import gr2.clc.drugstore.service.impl.idHandleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class category {
    @Id
    private String id;
    private String name;
    private List<String> cateEle;
}