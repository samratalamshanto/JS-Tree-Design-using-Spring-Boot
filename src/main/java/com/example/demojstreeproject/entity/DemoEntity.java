package com.example.demojstreeproject.entity;

import lombok.*;


import javax.persistence.*;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(unique = true)
    private String entityName;

    private long parentId;
}
