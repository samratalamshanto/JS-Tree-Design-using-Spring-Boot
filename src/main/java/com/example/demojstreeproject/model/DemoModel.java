package com.example.demojstreeproject.model;

import lombok.*;


import javax.persistence.*;
import java.util.List;
import java.util.Map;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoModel {

    private String parentName;
    private int isParent;
    private int hasChild;
    private List<Map<String,DemoModel>> child;
}

