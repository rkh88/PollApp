package com.poll.app.demo;

import com.poll.app.demo.beans.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

public class QuestionTest {


    @Test
    public void controllNumberOfAnswersByQuestionTest() {
        Poll poll = new Poll( 1L);

        Question question  = new Question(1L, "ed", QuestionType.MultiVarAnswer, poll);
        Answer answer1 = new Answer(question, poll, "A");
        Answer answer2 = new Answer(question, poll, "B");
        Answer answer3 = new Answer(question, poll, "C");

        Set<Answer> answers = Set.of(answer1, answer2, answer3);
        question.setAnswerList(answers);
        QuestionManager questionManager = new QuestionManager();
        Assert.assertTrue(questionManager.isQuestionValid(question));

        Question question1  = new Question(1L, "ed", QuestionType.TextAnswer, poll);
        question1.setAnswerList(answers);
        Assert.assertFalse(questionManager.isQuestionValid(question1));
        Question question12  = new Question(1L, "ed", QuestionType.OneVarAnswer, poll);
        question12.setAnswerList(answers);
        Assert.assertTrue(questionManager.isQuestionValid(question12));

    }

}
