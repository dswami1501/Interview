package com.swami.swamimvc.controller;

import com.swami.swamimvc.customerror.ValidationException;
import com.swami.swamimvc.models.User;
import com.swami.swamimvc.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("socialMedia")
public class CommentController {

    @Autowired
    InterviewService interviewService;
    @GetMapping("getComment/{commentTo}")
    public List<String> getComment(@PathVariable String commentTo){
        return interviewService.getAllCommentsForUser(commentTo);
    }

    @PostMapping("addComment/{commentFrom}/{commentTo}/{message}")
    public ResponseEntity<String> addComment(@PathVariable String commentFrom, @PathVariable String commentTo, @PathVariable String message){
        return interviewService.postComment(commentFrom,commentTo,message);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationExceptions(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
