package com.baigiamasis.sportstream;

import com.baigiamasis.sportstream.article.Article;
import com.baigiamasis.sportstream.article.ArticleService;
import com.baigiamasis.sportstream.comments.Comment;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class ArticleTest {

    @Mock
    private ArticleService articleService;

    @Autowired
    private MockMvc mockMvc;

    private Article article;
    private Comment comment1;
    private Comment comment2;

    @BeforeEach
    public void setUp() {
        article = new Article("Title", "image_url", "Text");
        comment1 = new Comment("John", "Comment 1");
        comment2 = new Comment("Jane", "Comment 2");
    }

    @Test
    public void testAddComment() {
        article.addComment(comment1);
        article.addComment(comment2);
        List<Comment> comments = article.getComments();
        assertEquals(2, comments.size());
        assertEquals(comment1, comments.get(0));
        assertEquals(comment2, comments.get(1));
    }

    @Test
    public void testGetters() {
        assertEquals("Title", article.getTitle());
        assertEquals("image_url", article.getImageUrl());
        assertEquals("Text", article.getText());
    }

    @Test
    public void testSetters() {
        article.setTitle("New Title");
        article.setImageUrl("new_image_url");
        article.setText("New Text");
        assertEquals("New Title", article.getTitle());
        assertEquals("new_image_url", article.getImageUrl());
        assertEquals("New Text", article.getText());
    }

    @Test
    public void testToString() {
        article.addComment(comment1);
        article.addComment(comment2);
        String expectedString = "Article{" +
                "id=0" +
                ", title='Title'" +
                ", text='Text'" +
                ", comments=[" +
                "Comment{id=0, name='John', content='Comment 1'}" +
                ", Comment{id=0, name='Jane', content='Comment 2'}" +
                "]" +
                "}";
        assertEquals(expectedString, article.toString());
    }

    @Test
    public void testId() {
        article.setId(10);
        assertEquals(10, article.getId());
    }



}