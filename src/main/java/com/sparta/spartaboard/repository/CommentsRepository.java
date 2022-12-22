package com.sparta.spartaboard.repository;

import com.sparta.spartaboard.entity.Board;
import com.sparta.spartaboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment,Long> {

    //List<Comment> findAllByOrderByModifiedAtDesc();
    List<Comment> findAllByBoardOrderByModifiedAtDesc(Board board);
}
