package com.baigiamasis.sportstream.article;

import com.baigiamasis.sportstream.comments.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleWithComments extends Article {
    private List<Comment> comments;

    public ArticleWithComments(Article article, List<Comment> comments) {
        super(article.getTitle(), article.getImageUrl(), article.getText());
        this.setComments(article.getComments());
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getId() {
        return super.getId();
    }

}