package com.baigiamasis.sportstream.comments;

import com.baigiamasis.sportstream.article.Article;
import com.baigiamasis.sportstream.article.ArticleRepository;
import com.baigiamasis.sportstream.article.ArticleService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ArticleService articleService;

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

//    @GetMapping("/news/article/{id}/comment")
//    public String articleCommentForm(@PathVariable("id") int id, Model model) {
//        model.addAttribute("article", articleService.getArticleById(id));
//        var comments = commentRepository.findAllByArticleId(id);
//
//        model.addAttribute("comments", comments);
//        model.addAttribute("comment", new Comment());
//        return "comments/comment";
//    }

//    @PostMapping("/news/article/{id}/comment")
//    public String createNewComment(@PathVariable("id") Integer id, @Valid Comment comment, BindingResult errors) {
//        if (errors.hasErrors()) {
//            logger.info("Yep, I have error: {}", comment);
//            errors.getAllErrors().forEach(System.out::println);
//            return "comments/comment";
//        }
//        logger.info("Comment: {}", comment);
//
//        Article article = articleService.getArticleById(id);
//
//        if(article == null){
//            return "redirect:/news";
//        }
//
//        List<Comment> comments = article.getComments();
//
//        if (comments == null) {
//            comments = new ArrayList<>();
//        }
//
//        comment.setArticle(article);
//        commentRepository.save(comment);
////////////
//        article.addComment(comment);
//        articleService.saveComments(id, comments);
//
//        logger.info("Article comment: {}", comment);
//        logger.info("Article comment: {}", article);
//        return  "redirect:/news/article/{id}";
//    }

//    @PostMapping("/news/article/{id}/comment")
//    public ResponseEntity<String> createNewComment(@PathVariable("id") Integer id, @Valid Comment comment, BindingResult errors) {
//        if (errors.hasErrors()) {
//            logger.info("Yep, I have error: {}", comment);
//            errors.getAllErrors().forEach(System.out::println);
//            return ResponseEntity.badRequest().body("Invalid comment data");
//        }
//        logger.info("Comment: {}", comment);
//
//        Article article = articleService.getArticleById(id);
//
//        if(article == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        List<Comment> comments = article.getComments();
//
//        if (comments == null) {
//            comments = new ArrayList<>();
//        }
//
//        comment.setArticle(article);
//        commentRepository.save(comment);
//
//        article.addComment(comment);
//        articleService.saveComments(id, comments);
//
//        logger.info("Article comment: {}", comment);
//        logger.info("Article comment: {}", article);
//        return ResponseEntity.created(URI.create("/news/article/" + id + "/comment/" + comment.getId())).build();
//    }
}
