package com.baigiamasis.sportstream.article;

//import com.baigiamasis.sportstream.comments.CommentRepository;

import com.baigiamasis.sportstream.comments.Comment;
import com.baigiamasis.sportstream.comments.CommentRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/news/article/{id}")
    public ResponseEntity<ArticleWithComments> articleWithCommentsById(@PathVariable int id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            List<Comment> comments = commentRepository.findByArticleId(id);
            ArticleWithComments articleWithComments = new ArticleWithComments(article.get(), comments);
            articleWithComments.setId(id);
            return new ResponseEntity<>(articleWithComments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/news")
    public ResponseEntity<Articles> articles() {
        return new ResponseEntity<>(
                new Articles(articleRepository.findAll()),
                HttpStatus.OK
        );
    }

    @PostMapping("news/article/new")
    public ResponseEntity<Article> createNewArticle(@Valid @RequestBody Article article) {
        Article created = articleRepository.save(article);
        logger.info("New article: {}", article);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @PostMapping("/news/article/{id}/edit")
    public ResponseEntity<Article> editArticle(@PathVariable("id") int id, @Valid @RequestBody Article updatedArticle, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setTitle(updatedArticle.getTitle());
            article.setImageUrl(updatedArticle.getImageUrl());
            article.setText(updatedArticle.getText());
            articleRepository.save(article);
            logger.info("Article updated: {}", article);
            return new ResponseEntity<>(article, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/news/article/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable int id) {

        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {

            articleRepository.delete(article.get());

            return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND);
        }
    }

}
