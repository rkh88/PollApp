package com.poll.app.demo.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.poll.app.demo.beans.Answer;
import com.poll.app.demo.beans.Poll;
import com.poll.app.demo.beans.Question;
import com.poll.app.demo.beans.QuestionManager;
import com.poll.app.demo.repositores.AnswerRepository;
import com.poll.app.demo.repositores.PollRepository;
import com.poll.app.demo.repositores.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.*;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;



    @PostMapping("/poll")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public ResponseEntity createNewPoll(@RequestBody Poll poll) {

        pollRepository.save(poll);
        return ResponseEntity.ok("Created a new poll");
    }

    @PutMapping("/poll")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public  ResponseEntity updatePoll(@RequestBody Poll poll) {

        Poll poll1 = pollRepository.findById(poll.getId()).orElse(null);
        if (Objects.nonNull(poll1)) {
            poll1.setDescription(poll.getDescription());
            poll1.setEndDate(poll.getEndDate());
            poll1.setName(poll.getName());
            pollRepository.save(poll1);
            return ResponseEntity.ok("Updated a new poll");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A poll doesn't exists");
    }

    @DeleteMapping("/poll")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public  ResponseEntity deletePoll(@RequestBody Poll poll) {
        if (pollRepository.existsById(poll.getId())) {
            pollRepository.deleteById(poll.getId());
            return ResponseEntity.ok("Question was  deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question was  not founded");
    }



    @RequestMapping(value= "/question", method=RequestMethod.POST,consumes="application/json",produces="application/json" )

    public ResponseEntity createNewQuestion(@Valid @RequestBody Question question) {

        if (pollRepository.existsById(question.getPoll().getId())) {
            System.out.println(question);
            if (!isValidQuestionsAndQuestionType(question)) {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question has wrong type");
            }
            Set<Answer> answers = new HashSet<>(question.getAnswerList());
            question.getAnswerList().clear();


            Question q1 = questionRepository.save(question);
            q1.setAnswerList(answers);
            answers.forEach(item -> {
                item.setQuestion(q1);
                item.setPoll(q1.getPoll());
            });
            answerRepository.saveAll(answers);
            return ResponseEntity.ok("Created a new question");

        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                String.format("The poll with id %d was not founded", question.getPoll().getId() ));
    }

    private boolean isValidQuestionsAndQuestionType(Question question) {
        return new QuestionManager().isQuestionValid(question);
    }

    @PutMapping("/question")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public ResponseEntity changeQuestion(@RequestBody Question question) {
        Question question1 = questionRepository.findById(question.getId()).orElse(null);
        if (Objects.nonNull(question1)) {
            question1.setQuestionText(question.getQuestionText());
            question1.setQuestionType(question.getQuestionType());
            question1.setPoll(question.getPoll());
            questionRepository.save(question1);
            return ResponseEntity.ok("The question was changed");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question is not founded");
    }

    @DeleteMapping("/question")
    public ResponseEntity deleteQuestion(@RequestBody Question question) {
        if (questionRepository.existsById(question.getId())) {
            questionRepository.deleteById(question.getId());
            return ResponseEntity.ok("Question was  deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question is not founded");
    }
}
