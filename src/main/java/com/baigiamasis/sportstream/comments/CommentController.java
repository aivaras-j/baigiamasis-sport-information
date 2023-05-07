package com.baigiamasis.sportstream.comments;

import com.baigiamasis.sportstream.article.Article;
import com.baigiamasis.sportstream.article.ArticleRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
public class CommentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    public CommentController(@Autowired CommentRepository repository) {
        this.commentRepository = repository;
    }


    @PostMapping("/news/article/{id}/comment")
    public ResponseEntity<Comment> createNewComment(@PathVariable("id") int id, @Valid @RequestBody Comment comment, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            comment.setArticle(article);
            commentRepository.save(comment);

            logger.info("New comment: {}", comment);

            return new ResponseEntity<>(comment, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/news/article/{id}/commentDelete")
    public ResponseEntity<String> deleteComment(@PathVariable int id) {

        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {

            commentRepository.delete(comment.get());

            return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
        }
    }

}
