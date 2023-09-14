package com.amita.SpringBoot.dao;

import com.amita.SpringBoot.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {// Quiz is the class
}
