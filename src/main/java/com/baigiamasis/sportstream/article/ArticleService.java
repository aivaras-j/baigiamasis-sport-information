package com.baigiamasis.sportstream.article;

import com.baigiamasis.sportstream.comments.Comment;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository repository) {

        this.articleRepository = repository;
    }

    public Article createAndEditArticle(Article article) {

        return articleRepository.save(article);
    }

    public void saveComments(Integer id, List<Comment> comments) {
        Article article = getArticleById(id);
        article.setComments(comments);
        createAndEditArticle(article);
    }

    public Article getArticleById(Integer id) {

        return articleRepository.findById(id).get();
    }

    public List<Article> getAllArticles() {

        return articleRepository.findAll();
    }

    public Page<Article> page(int currentPage) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, 3);
        return articleRepository.findAll(pageRequest);

    }

    public void deleteArticleById(Integer id) {

        articleRepository.deleteById(id);
    }


//    public void addComments(Integer id, List<Comment> newComments) {
//        Article article = getArticleById(id);
//        List<Comment> comments = article.getComments();
//        comments.addAll(newComments);
//        article.setComments(comments);
//        createAndEditArticle(article);
//    }

//    public void saveComments(Integer id, List<Comment> comments) {
//        Article article = getArticleById(id);
//        List<Comment> existingComments = article.getComments();
//        existingComments.addAll(comments);
//        article.setComments(existingComments);
//        createAndEditArticle(article);
//    }




}
