package com.example.demojstreeproject.controller;

import com.example.demojstreeproject.entity.DemoEntity;
import com.example.demojstreeproject.model.DemoModel;
import com.example.demojstreeproject.service.DemoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/demodata")
public class DemoController {
    final private DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }


    @GetMapping
    public List<DemoEntity> welcome() {
        return demoService.getData();
    }


    @GetMapping("single/{id}")
    public Map<String, DemoModel> getByName(@PathVariable("id") int entityId) {
        return demoService.getDataById(entityId);
    }

    @PostMapping
    public String saveEntity(@RequestBody DemoEntity entity) {
        return demoService.saveEntity(entity);
    }
}
