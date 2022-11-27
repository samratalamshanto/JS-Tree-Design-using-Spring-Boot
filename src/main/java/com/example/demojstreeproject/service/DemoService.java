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


    public Map<String, DemoModel> recursiveHelperFunction(int Id, List<DemoEntity> fullEntityList) {
        Map<String, DemoModel> map = new HashMap<>();

        DemoEntity entity = demoRepository.findById((long) Id).get();

        DemoModel model = new DemoModel();

        String entityName = entity.getEntityName();

        int entityParentId = (int) entity.getParentId();

        if (Objects.equals(entityParentId, 0)) {
            model.setIsParent(1);
            model.setParentName("");
        } else {
            model.setIsParent(0);
            model.setHasChild(0);
            model.setParentName(fullEntityList.get((int) entity.getParentId() - 1).getEntityName());  //0 index list so -1 needed
        }


        Iterator<DemoEntity> itr = fullEntityList.iterator();
        while (itr.hasNext()) {
            DemoEntity curEntity = itr.next();

            int curEntityParentId = (int) curEntity.getParentId();
            int entityId = (int) Id;


            if (curEntityParentId == entityId) {
                model.setHasChild(1);
                try {
                    Map<String, DemoModel> map1 = recursiveHelperFunction((int) curEntity.getId(), fullEntityList);
                    if (!map.isEmpty()) {
                        return map1;  //return map
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        //when map is empty, it will come here and set null list []
        List<Map<String, DemoModel>> nullModel = new ArrayList<>();
        model.setChild(nullModel);
        map.put(entityName, model);

        return map;
    }


    public Map<String, DemoModel> recursiveFunction(int Id, List<DemoEntity> fullEntityList, List<Map<String, DemoModel>> modelList) {
        Map<String, DemoModel> map = new HashMap<>();

        DemoEntity entity = demoRepository.findById((long) Id).get();

        DemoModel curModel = new DemoModel();

        String entityName = entity.getEntityName();

        int parentId = (int) entity.getParentId();

        if (Objects.equals(parentId, 0)) {
            curModel.setIsParent(1);
            curModel.setParentName("");
        } else {
            curModel.setIsParent(0);
            curModel.setHasChild(0);
            curModel.setParentName(fullEntityList.get((int) entity.getParentId() - 1).getEntityName());
        }


        Iterator<DemoEntity> itr = fullEntityList.iterator(); // O index list
        while (itr.hasNext()) {
            DemoEntity curEntity = itr.next();

            int curEntityId = (int) curEntity.getId();

            int curEntityParentId = (int) curEntity.getParentId();
            int entityId = (int) Id;


            if (curEntityParentId == entityId) {

                curModel.setHasChild(1);
                Map<String, DemoModel> map1 = recursiveHelperFunction((int) curEntity.getId(), fullEntityList);

                if (!map1.isEmpty()) {
                    for (DemoModel mapModelValue : map1.values()) {
                        int childDemoModel1 = mapModelValue.getHasChild();  //if mapModelValue has child then we call again the recursiveFunction()
                        log.info("childModel1: {}", childDemoModel1);
                        if (childDemoModel1 == 1) {
                            List<Map<String, DemoModel>> modelList1 = new ArrayList<>();
                            map1 = recursiveFunction(curEntityId, fullEntityList, modelList1);  //otherwise it will not return all values.
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
        curModel.setChild(modelList);
        map.put(entityName, curModel);

        return map;

    }

    public Map<String, DemoModel> getDataById(int Id) {
        List<DemoEntity> list = demoRepository.findAll();
        List<Map<String, DemoModel>> modelList = new ArrayList<>();
        return recursiveFunction(Id, list, modelList);
    }
}
