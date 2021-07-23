package com.poll.app.demo.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Question.class)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Question question;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="id_poll", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Poll poll;

    private String answer;


    public Answer(Question question, Poll poll, String answer) {
        this.question = question;
        this.poll = poll;
        this.answer = answer;
    }


    public Answer() {
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    @JsonCreator
    public Answer(Long id, Question question, Poll poll, String answer) {
        this.id = id;
        this.question = question;
        this.poll = poll;
        this.answer = answer;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                "question " + question.getId() +
                '}';
    }
}
