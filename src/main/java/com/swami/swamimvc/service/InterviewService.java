package com.swami.swamimvc.service;

import com.swami.swamimvc.customerror.ValidationException;
import com.swami.swamimvc.dao.CommentsDao;
import com.swami.swamimvc.dao.UserDao;
import com.swami.swamimvc.models.Comments;
import com.swami.swamimvc.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
public class InterviewService {

    @Autowired
    UserDao userRepo;
    @Autowired
    CommentsDao commentsRepo;

    @Transactional
    public List<String> getAllCommentsForUser(String commentTo) {
        return commentsRepo.findMessagesForCommentTo(commentTo);
    }

    @Transactional
    public ResponseEntity<String> postComment(String commentFrom, String commentTo, String message) {
        // Check if commentFrom contains special characters or is empty
        if (commentFrom.isEmpty() || !commentFrom.matches("^[a-zA-Z]+$")) {
            throw new ValidationException("Invalid Request");
        }

        // Check if commentTo contains special characters or is empty
        if (commentTo.isEmpty() || !commentTo.matches("^[a-zA-Z]+$")) {
            throw new ValidationException("Invalid Request");
        }

        // Check if the 'commentFrom' user exists, and if not, create it
        User commentFromUser = userRepo.findByUserNameIgnoreCase(commentFrom);
        if (commentFromUser == null) {
            // Create a new user and set the username
            commentFromUser = new User();
            commentFromUser.setUserName(commentFrom);

            // Save the new user to the "user" table
            userRepo.save(commentFromUser);
        }

        // Check if the 'commentTo' user exists, and if not, create it
        User commentToUser = userRepo.findByUserNameIgnoreCase(commentTo);
        if (commentToUser == null) {
            // Create a new user and set the username
            commentToUser = new User();
            commentToUser.setUserName(commentTo);

            // Save the new user to the "user" table
            userRepo.save(commentToUser);
        }

        // Next, create a new Comments object with the provided data
        Comments newComment = new Comments();
        newComment.setCommentFrom(commentFrom);
        newComment.setCommentTo(commentTo);
        newComment.setMessage(message);

        // Set the posting user (commentFromUser) as the foreign key
        newComment.setUser(commentFromUser);

        // Save the new comment to the "comments" table
        commentsRepo.save(newComment);

        return ResponseEntity.ok("Comment added successfully");
    }
}
