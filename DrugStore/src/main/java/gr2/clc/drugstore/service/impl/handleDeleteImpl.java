package gr2.clc.drugstore.service.impl;

import gr2.clc.drugstore.entity.categoryEle;
import gr2.clc.drugstore.service.categoryEleService;
import gr2.clc.drugstore.service.categoryService;
import gr2.clc.drugstore.service.handleDelete;
import gr2.clc.drugstore.service.medicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class handleDeleteImpl implements handleDelete {
    private medicineService repoMedicine;
    private categoryService repoCate;
    private categoryEleService repoCateEle;

    @Autowired
    public void setMedicineRepo(medicineService repoMedicine) {
        this.repoMedicine = repoMedicine;
    }

    @Autowired
    public void setCateRepo(categoryService repoCate) {
        this.repoCate = repoCate;
    }

    @Autowired
    public void setCateEleRepo(categoryEleService repoCateEle) {
        this.repoCateEle = repoCateEle;
    }

    @Override
    public void setCateForMedicine(String idCate) {
        repoMedicine.getByCateID(idCate).forEach(s -> {
            s.setCategory("CA09");
            repoMedicine.saveOrUpdate(s);
        });
    }

    @Override
    public void setCateEleForCate(categoryEle cateEle) {

    }

    @Override
    public void setCateEleForMedicine(String idCateEle) {

    }
}
