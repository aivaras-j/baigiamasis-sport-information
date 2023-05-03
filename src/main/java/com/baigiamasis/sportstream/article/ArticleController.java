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
//@RequestMapping("/news")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/page/{pageNumber}")
    public String page(@PathVariable("pageNumber") int currentPage, Model model) {
        Page<Article> page = articleService.page(currentPage);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalArticles", page.getTotalElements());
        model.addAttribute("articles", page.getContent());

        return "article/news";
    }

    @GetMapping
    public String pages(Model model) {

        return page(1, model);
    }

    //////////////////

    @GetMapping("/news/article/{id}")
    public ResponseEntity<ArticleWithComments> articleWithCommentsById(@PathVariable int id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            List<Comment> comments = commentRepository.findByArticleId(id);
            ArticleWithComments articleWithComments = new ArticleWithComments(article.get(), comments);
            articleWithComments.setId(id); // Nustatykite id tiesiogiai į ArticleWithComments objektą
            return new ResponseEntity<>(articleWithComments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @GetMapping("/news/article/{id}")
//    public ResponseEntity<ArticleWithComments> articleById(@PathVariable int id) {
//        Optional<Article> article = articleRepository.findById(id);
//        if (article.isPresent()) {
//            List<Comment> comments = commentRepository.findByArticleId(id);
//            ArticleWithComments articleWithComments = new ArticleWithComments(article.get(), comments);
//            return new ResponseEntity<>(articleWithComments, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @GetMapping("/news/article/{id}")
//    public ResponseEntity<ArticleWithComments> articleWithCommentsById(@PathVariable int id) {
//        Optional<Article> article = articleRepository.findById(id);
//        logger.info(" Article111: {}", article);
//        if (article.isPresent()) {
//            List<Comment> comments = commentRepository.findByArticleId(id);
//            ArticleWithComments articleWithComments = new ArticleWithComments(article.get(), comments);
//            return new ResponseEntity<>(articleWithComments, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @GetMapping("/news/article/{id}")
//    public ResponseEntity<ArticleWithComments> articleById(@PathVariable int id) {
//        Optional<Article> article = articleRepository.findById(id);
//        if (article.isPresent()) {
//            ArticleWithComments articleWithComments = new ArticleWithComments(article.get());
//            return new ResponseEntity<>(articleWithComments, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @GetMapping("/news/article/{id}")
//    public ResponseEntity<Article> articleById(@PathVariable int id) {
//        Optional<Article> article = articleRepository.findById(id);
//        if (article.isPresent()) {
//            return new ResponseEntity<>(article.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

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


////

//    @PostMapping("/article/new")
//    public String createNewArticle(@Valid Article article, BindingResult errors, Model model) {
//        if (errors.hasErrors())
//            return "article/newArticle";
//
//        Article created = articleService.createAndEditArticle(article);
//        model.addAttribute("article", created);
//
//        logger.info("New article: {}", article);
//
//        return "redirect:/news/article/" + created.getId();
//    }

//    @GetMapping("/article/new")
//    public String createNewArticle(Model model) {
//        model.addAttribute("article", new Article());
//        return "article/newArticle";
//    }

//    @GetMapping("/article/{id}")
//    public String article(@PathVariable("id") int id, Model model) {
//        model.addAttribute("article", articleService.getArticleById(id));
//        return "article/article";
//
//    }

//    @GetMapping("/article/{id}/edit")
//    public String showArticleEdit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("article", articleService.getArticleById(id));
//        logger.info("Edit article");
//
//        return "article/articleEdit";
//    }

//    @PostMapping("/article/{id}/edit")
//    public String editedArticle(@PathVariable("id") int id, @Valid Article article, BindingResult errors, Model model) {
//        logger.info("Edited article: {}", article);
//
//        if (errors.hasErrors()) {
//            return "article/articleEdit";
//        }
//
//        Article updated = articleService.createAndEditArticle(article);
//        model.addAttribute("article", updated);
//
//        return "redirect:/news/article/" + updated.getId();
//    }


}
