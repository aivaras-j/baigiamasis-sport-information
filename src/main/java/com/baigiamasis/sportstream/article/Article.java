package com.baigiamasis.sportstream.article;

import com.baigiamasis.sportstream.comments.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


//import com.baigiamasis.sportstream.comments.Comment;
import java.util.ArrayList;
import java.util.List;

@Entity
//@RequiredArgsConstructor
//@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @NonNull
    @NotEmpty(message = "Article title cannot be empty")
    private String title;

    @Column(columnDefinition = "blob")
    @NonNull
    @NotEmpty(message = "Article imageUrl cannot be empty")
    private String imageUrl;

    @Column(columnDefinition = "blob")
    @NonNull
    @Size(
            min=5,
            message = "Text lenght is too short. Must be minimum 5 text simbols."
    )
    private String text;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Article() {

    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setArticle(this);
    }



    public Article(@NonNull String title, @NonNull String imageUrl, @NonNull String text) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.text = text;
        this.comments = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getText() {
        return text;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }



    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", comments=" + comments +
                '}';
    }
}