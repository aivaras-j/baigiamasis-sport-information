package com.baigiamasis.sportstream.sportType;

import com.baigiamasis.sportstream.article.Article;
import com.baigiamasis.sportstream.article.Articles;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SportTypes {

    @NonNull
    private List<SportType> sportTypes;

    public static SportTypes of(List<SportType> sportTypes) {

        return new SportTypes(sportTypes);
    }
}