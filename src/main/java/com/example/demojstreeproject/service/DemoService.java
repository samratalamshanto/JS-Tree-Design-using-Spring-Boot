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

    public Map<String, DemoModel> recursiveHelperFunctionHelper(int Id, List<DemoEntity> fullEntityList) {
        Map<String, DemoModel> map = new HashMap<>();

        DemoEntity demo = demoRepository.findById((long) Id).get();

        DemoModel demoModel = new DemoModel();

        String entityName = demo.getEntityName();

        int parentId = (int) demo.getParentId();

        if (Objects.equals(parentId, 0)) {
            demoModel.setIsParent(1);
            demoModel.setParentName("");
        } else {
            demoModel.setIsParent(0);
            demoModel.setHasChild(0);
            demoModel.setParentName(fullEntityList.get((int) demo.getParentId() - 1).getEntityName());
        }


        Iterator<DemoEntity> itr = fullEntityList.iterator();
        while (itr.hasNext()) {
            DemoEntity demo1 = itr.next();

            int demo1Id = (int) demo1.getParentId();
            int Id1 = (int) Id;


            if (demo1Id == Id1) {

                demoModel.setHasChild(1);

                String demo1Entity = demo1.getEntityName();


                try {
                    Map<String, DemoModel> map1 = recursiveHelperFunctionHelper((int) demo1.getId(), fullEntityList);
                    if (!map.isEmpty()) {
                        return map1;
                    }

//
//                    if (modelList.contains(map1)) {
//                        return map;
//                    } else {
//                        log.info("maps :{}", map1);
//                        if(modelList.size()==0)
//                        {
//                            modelList.add(map1);
//                        }
//
//                    }

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }


            }
        }
        //demoModel.setChild(modelList);
        map.put(entityName, demoModel);

        return map;

    }


    public Map<String, DemoModel> recursiveHelperFunction(int Id, List<DemoEntity> fullEntityList, List<Map<String, DemoModel>> modelList) {
        Map<String, DemoModel> map = new HashMap<>();

        DemoEntity demo = demoRepository.findById((long) Id).get();

        DemoModel demoModel = new DemoModel();

        String entityName = demo.getEntityName();

        int parentId = (int) demo.getParentId();

        if (Objects.equals(parentId, 0)) {
            demoModel.setIsParent(1);
            demoModel.setParentName("");
        } else {
            demoModel.setIsParent(0);
            demoModel.setHasChild(0);
            demoModel.setParentName(fullEntityList.get((int) demo.getParentId() - 1).getEntityName());
        }


        Iterator<DemoEntity> itr = fullEntityList.iterator();
        while (itr.hasNext()) {
            DemoEntity demo1 = itr.next();

            int demo1Id = (int) demo1.getId();

            int demo1ParentId = (int) demo1.getParentId();
            int Id1 = (int) Id;


            if (demo1ParentId == Id1) {

                demoModel.setHasChild(1);

                String demo1Entity = demo1.getEntityName();


                Map<String, DemoModel> map1 = recursiveHelperFunctionHelper((int) demo1.getId(), fullEntityList);
                if (!map1.isEmpty()) {

                    for (DemoModel demoModel1 : map1.values()) {
                        int childDemoModel1 = demoModel1.getHasChild();
                        log.info("childModel1: {}", childDemoModel1);
                        if (childDemoModel1 == 1) {
                            List<Map<String, DemoModel>> modelList1y = new ArrayList<>();
                            map1 = recursiveHelperFunction(demo1Id, fullEntityList, modelList1);
                            log.info("childModel map: {}", map1);
                        }
                    }

                }


                log.info("maps :{}", map1);
                try {

                    modelList.add(map1);


                    log.info("maps equal:??: {}", modelList.contains(map1));

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }


            }
        }
        demoModel.setChild(modelList);
        map.put(entityName, demoModel);

        return map;

    }

    public Map<String, DemoModel> getDataById(int Id) {
        List<DemoEntity> list = demoRepository.findAll();
        List<Map<String, DemoModel>> modelList = new ArrayList<>();
        return recursiveHelperFunction(Id, list, modelList);
    }
}
