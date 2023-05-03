package com.baigiamasis.sportstream.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

  //  List<Comment> findAllByArticleId(int articleId);
    Optional<Comment> findById(Integer id);

    List<Comment> findByArticleId(int articleId);
}
