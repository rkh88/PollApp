package com.poll.app.demo.services;

import com.poll.app.demo.beans.Poll;
import com.poll.app.demo.beans.Result;
import com.poll.app.demo.repositores.AnswerRepository;
import com.poll.app.demo.repositores.PollRepository;
import com.poll.app.demo.repositores.QuestionRepository;
import com.poll.app.demo.repositores.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private QuestionRepository questionRepository;


    @Autowired
    private PollRepository pollRepository;


    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ResultRepository resultRepository;


    @Override
    public List<Poll> getActivePolls() {
        return pollRepository.findAll();
    }

    @Override
    public List<Result> getPassedPolls(Long id) {
        List<Result> polls =  resultRepository.findAllByUserId(id);
        return polls;
    }

    @Override
    public void passPoll(Result answer) {
        resultRepository.save(answer);
    }

    @Override
    public boolean existsPoll(Long id) {
        return pollRepository.existsById(id);
    }

    @Override
    public boolean existsQuestion(Long id) {
        return questionRepository.existsById(id);
    }
}
