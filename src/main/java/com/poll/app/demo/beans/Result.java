package com.poll.app.demo.beans;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.poll.app.demo.utils.EntityIdResolver;
import javax.validation.constraints.NotNull;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        scope = Result.class,
        resolver = EntityIdResolver.class,
        property = "id")
public class Result  implements Serializable  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_question", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Question question;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_poll", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    private Poll poll;

    private LocalDateTime dateTime = LocalDateTime.now();

    @NotNull
    private String answer;

    public Result(Long userId, Question question, Poll poll, LocalDateTime dateTime, String answer) {
        this.userId = userId;
        this.question = question;
        this.poll = poll;
        this.dateTime = dateTime;
        this.answer = answer;
    }

    public Result() {
    }

    public Result(Long userId, Question question, Poll poll, String answer) {
        this.userId = userId;
        this.question = question;
        this.poll = poll;
        this.answer = answer;
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

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", userId=" + userId +
                ", question=" + question +
                ", poll=" + poll +
                ", dateTime=" + dateTime +
                ", answer='" + answer + '\'' +
                '}';
    }
}
