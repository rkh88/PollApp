package com.poll.app.demo.repositores;

import com.poll.app.demo.beans.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ResultRepository  extends JpaRepository<Result, Long> {


    List<Result> findAllByUserId(Long id);
}
