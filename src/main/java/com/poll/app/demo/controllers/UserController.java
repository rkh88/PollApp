package com.poll.app.demo.controllers;

import com.poll.app.demo.beans.Poll;
import com.poll.app.demo.beans.Result;
import com.poll.app.demo.exceptions.ResultException;
import com.poll.app.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.List;
// localhost:8080//api/poll
@RestController
@RequestMapping("/api/poll")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/passingpoll")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public ResponseEntity passingPoll(@Valid @RequestBody Result result){

        userService.passPoll(result);
        return ResponseEntity.ok("Poll was passed.");
    }



    @GetMapping("/allpolls")
    public ResponseEntity<List<Poll>> getPassedPolls(){
        List<Poll> polls =  userService.getActivePolls();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(polls);
    }


    @GetMapping("/passedpolls/{id}")
    public ResponseEntity<List<Result>> getPassedPolls(@PathVariable("id") Long id){
        List<Result> polls =  userService.getPassedPolls(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(polls);
    }


}
