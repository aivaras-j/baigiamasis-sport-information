package com.baigiamasis.sportstream.comments;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Comments {

    @NonNull
    private List<Comment> comments;

    public static Comments of(List<Comment> comments) {
        return new Comments(comments);
    }
}