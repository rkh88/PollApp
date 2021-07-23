package com.poll.app.demo.beans;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.poll.app.demo.utils.EntityIdResolver;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        scope = Question.class,
        resolver = EntityIdResolver.class,
        property = "id")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable =  false)
    private String questionText;

    @Enumerated(EnumType.ORDINAL)
    private QuestionType questionType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_poll", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private Poll poll;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
    private Set<Answer> answerList = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question")
    private Set<Result> results = new HashSet<>();


    public Question(Long id) {
        this.id = id;
    }

    public Question(String questionText, QuestionType questionType) {
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public Question(String questionText, QuestionType questionType, Poll poll) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.poll = poll;
    }

    public Question(Long id, String questionText, QuestionType questionType) {
        this.id = id;
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public Question(Long id, String questionText, QuestionType questionType, Poll poll) {
        this.id = id;
        this.questionText = questionText;
        this.questionType = questionType;
        this.poll = poll;
    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }


    public void setAnswer(Set<Answer> answerSet) {
        this.answerList = answerSet;
    }


    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", questionType=" + questionType +
                ", poll=" + poll +
               // ", answerList=" + answerList +
                ", results=" + results +
                '}';
    }


    public Set<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(Set<Answer> answerList) {
        this.answerList = answerList;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }


}
