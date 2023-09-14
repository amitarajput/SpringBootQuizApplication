package com.amita.SpringBoot.service;

import com.amita.SpringBoot.dao.QuestionDao;
import com.amita.SpringBoot.dao.QuizDao;
import com.amita.SpringBoot.model.Question1;
import com.amita.SpringBoot.model.QuestionWrapper;
import com.amita.SpringBoot.model.Quiz;
import com.amita.SpringBoot.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Create a Quiz(means create a table) stored in DB. questions are repeated in this case store data(quiz and question table) in DB how we will do it. using mapping
@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;// autowired so that we can get questions

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) { // now create a quiz
        List<Question1> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return  new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) { // need to fetch quiz objects from the DB
        Optional<Quiz> quiz = quizDao.findById(id);// optional use for findby id not return throgh null use optional
        List<Question1> questionFromDB = quiz.get().getQuestions();// convert question into question wrapper// hold questions
        List<QuestionWrapper> questionsForUser = new ArrayList<>();// object now add data into list
        for(Question1 q : questionFromDB){ QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3());
     questionsForUser.add(qw);// add questions qw to list
        }
                return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
Quiz quiz = quizDao.findById(id).get();
List<Question1> questions = quiz.getQuestions();int right = 0; int i = 0;
for(Response response : responses){if(response.getResponse().equals(questions.get(i).getCorrect_answer()))
right++;
i++;
} return new ResponseEntity<>(right, HttpStatus.OK);
}
}