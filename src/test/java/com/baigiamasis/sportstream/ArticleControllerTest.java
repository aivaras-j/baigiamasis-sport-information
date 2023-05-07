package com.baigiamasis.sportstream;

import com.baigiamasis.sportstream.article.*;
import com.baigiamasis.sportstream.comments.Comment;
import com.baigiamasis.sportstream.comments.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ArticleControllerTest {

    @InjectMocks
    private ArticleController articleController;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testArticles() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());
        when(articleRepository.findAll()).thenReturn(articles);

        ResponseEntity<Articles> responseEntity = articleController.articles();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(articles.size(), responseEntity.getBody().getArticles().size());

        verify(articleRepository, times(1)).findAll();
    }

    @Test
    public void testArticleWithCommentsByIdNotFound() {
        int articleId = 1;
        when(articleRepository.findById(articleId)).thenReturn(Optional.empty());

        ResponseEntity<ArticleWithComments> responseEntity = articleController.articleWithCommentsById(articleId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        verify(articleRepository, times(1)).findById(articleId);
        verifyNoMoreInteractions(commentRepository);
    }


}
