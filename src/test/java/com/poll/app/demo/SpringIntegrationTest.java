package com.poll.app.demo;

import com.poll.app.demo.beans.Poll;
import com.poll.app.demo.beans.Question;
import com.poll.app.demo.beans.QuestionType;
import com.poll.app.demo.repositores.PollRepository;
import com.poll.app.demo.repositores.QuestionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class, H2JpaConfig.class})
public class SpringIntegrationTest {


    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void testPollRepository() {
        Poll poll = new Poll( "Name", LocalDate.now(), LocalDate.now(), "AAA");
        Poll result = pollRepository.save(poll);
        Assert.assertEquals(poll.getName(), result.getName());
    }

    @Test
    public void tesQuestionRepository() {
        Poll poll = new Poll( "Name", LocalDate.now(), LocalDate.now(), "AAA");
        Poll result = pollRepository.save(poll);
        Question question  = new Question("ed", QuestionType.MultiVarAnswer, result);
        Question question1 = questionRepository.save(question);
        Assert.assertEquals(question.getQuestionText(), question1.getQuestionText());
        Assert.assertEquals(question.getPoll().getId(), question1.getPoll().getId());
    }


}
