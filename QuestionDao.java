package com.amita.SpringBoot.dao;

import com.amita.SpringBoot.model.Question1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question1,Integer> {
    List<Question1> findByCategory(String category);
    @Query(value = "SELECT * FROM question1 q where q.category=:category ORDER BY RANDOM() LIMIT :numQ,", nativeQuery = true)// get data from table

    List<Question1> findRandomQuestionsByCategory(String category, int numQ);
}









































































































