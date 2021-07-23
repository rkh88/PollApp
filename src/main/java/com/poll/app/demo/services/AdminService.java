package com.poll.app.demo.services;

import com.poll.app.demo.beans.Poll;
import com.poll.app.demo.beans.Question;

public interface AdminService {

    void createQuestion(Question question);

    void createPoll(Poll poll);

    void deleteQuestion();
}
