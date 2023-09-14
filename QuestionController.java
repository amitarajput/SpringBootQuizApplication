package com.amita.SpringBoot.controller;

import com.amita.SpringBoot.model.Question1;
import com.amita.SpringBoot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question1>> getAllQuestions() {//return list of questions create new object response entity
        return questionService.getAllQuestions();

    }
    @GetMapping("category/{category}")// fetch question by category Java or python
    public List<Question1> getQuestionsByCategory(@PathVariable String category) {
        return (List<Question1>) questionService.getQuestionsByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question1 question1){ // add question
        return questionService.addQuestion(question1);

    }
}