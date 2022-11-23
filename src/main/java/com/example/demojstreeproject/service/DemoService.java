package com.example.demojstreeproject.service;

import com.example.demojstreeproject.entity.DemoEntity;
import com.example.demojstreeproject.model.DemoModel;
import com.example.demojstreeproject.repository.DemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class DemoService {
    final private DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public String saveEntity(DemoEntity entity) {
        try {
            demoRepository.save(entity);
            return "Save Successfully.";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "Data save unsuccessful.";
        }
    }

    public List<DemoEntity> getData() {
        //  List<DemoEntity> x = demoRepository.findAll();
        return demoRepository.findAll();
    }

    public Map<String, DemoModel> recursiveHelperFunction(int Id, List<DemoEntity> fullEntityList, List<Map<String,DemoModel>> modelList) {
        Map<String, DemoModel> map = new HashMap<>();

        DemoEntity demo = demoRepository.findById((long) Id).get();

        DemoModel demoModel = new DemoModel();

        String entityName = demo.getEntityName();
        System.out.println(demo);
        int parentId = (int) demo.getParentId();

        if (Objects.equals(parentId, 0)) {
            demoModel.setIsParent(1);
            demoModel.setParentName("");
        } else {
            demoModel.setIsParent(0);
            demoModel.setHasChild(0);
            demoModel.setParentName(fullEntityList.get((int) demo.getParentId()-1).getEntityName());
        }

        for (int i = 0; i < fullEntityList.size(); i++) {
            DemoEntity demo1 = fullEntityList.get(i);
            Map<String,DemoModel> map1= new HashMap<>();


            modelList.add(recursiveHelperFunction(,fullEntityList,modelList));



            demoModel.setChild(modelList);
        }
        map.put(entityName, demoModel);

        return map;

    }

    public Map<String, DemoModel> getDataById(int Id) {
        List<DemoEntity> list = getData();
        List<Map<String,DemoModel>> modelList = new ArrayList<>();
        return recursiveHelperFunction(Id, list, modelList);
    }
}
