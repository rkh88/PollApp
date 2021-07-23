package com.poll.app.demo.services;

import com.poll.app.demo.beans.Poll;
import com.poll.app.demo.beans.Result;

import java.util.List;

public interface UserService {
    List<Poll> getActivePolls();

    List<Result> getPassedPolls(Long id);

    void passPoll(Result answer);

    boolean existsPoll(Long id);

    boolean existsQuestion(Long id);
}
