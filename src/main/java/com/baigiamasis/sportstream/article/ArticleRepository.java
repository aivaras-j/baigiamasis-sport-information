package com.baigiamasis.sportstream.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> findById(int id);
    Optional<Article> findByTitle(String title);

    void deleteById(int id);
}
