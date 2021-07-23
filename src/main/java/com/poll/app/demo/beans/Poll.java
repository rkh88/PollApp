package com.poll.app.demo.beans;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.poll.app.demo.utils.EntityIdResolver;
import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;



@Entity
@Table(name = "polls")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        scope = Poll.class,
        resolver = EntityIdResolver.class,
        property = "id")
public class Poll  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

   // @NotEmpty(message = "Propety startDate is empty but this is not allowed")
    @Column(name="startDate",  updatable = false, nullable = false)
    private LocalDate startDate;

    //@Temporal(TemporalType.TIMESTAMP)
    //@CreatedDate
    private LocalDate endDate;
    private String description;


    @OneToMany(targetEntity = Question.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "poll")
    private Set<Question> questions;

    @JsonIgnore
    @OneToMany(targetEntity =  Result.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "poll")
    private Set<Result> results = new HashSet<>();

    public Poll(Long id) {
        this.id = id;
    }


    public Poll(String name, LocalDate startDate, LocalDate endDate, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.questions = new HashSet<>();
    }

    public Poll() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poll)) return false;
        Poll poll = (Poll) o;
        return Objects.equals(id, poll.id) &&
                Objects.equals(name, poll.name) &&
                Objects.equals(startDate, poll.startDate) &&
                Objects.equals(endDate, poll.endDate) &&
                Objects.equals(description, poll.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, description);
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                '}';
    }
}

