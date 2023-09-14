package com.amita.SpringBoot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity// for create a table we need entity, data from lombok
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToMany // because we have quiz with mutiple questions
    private List<Question1> questions;// one quiz has mutilple questions thats why list of questions
}

