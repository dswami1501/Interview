package com.swami.swamimvc.dao;

import com.swami.swamimvc.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsDao extends JpaRepository<Comments, Integer> {
    /*@Query("SELECT c.message FROM Comments c WHERE c.commentTo = :commentTo")*/
    @Query("SELECT c.message FROM Comments c WHERE LOWER(c.commentTo) = LOWER(:commentTo)")
    List<String> findMessagesForCommentTo(@Param("commentTo") String commentTo);

}
