package com.baigiamasis.sportstream.article;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Articles {

    @NonNull
    private List<Article> articles;

    public static Articles of(List<Article> articles) {
        return new Articles(articles);
    }
}

