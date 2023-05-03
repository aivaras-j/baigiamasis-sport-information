package com.baigiamasis.sportstream;

import com.baigiamasis.sportstream.article.Article;
import com.baigiamasis.sportstream.article.ArticleRepository;
//import com.baigiamasis.sportstream.comments.Comment;
//import com.baigiamasis.sportstream.comments.CommentRepository;
import com.baigiamasis.sportstream.comments.Comment;
import com.baigiamasis.sportstream.comments.CommentRepository;
import com.baigiamasis.sportstream.event.Event;
import com.baigiamasis.sportstream.event.EventRepository;
import com.baigiamasis.sportstream.sportType.SportType;
import com.baigiamasis.sportstream.sportType.SportTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SportStreamApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SportStreamApplication.class, args);
    }

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    SportTypeRepository sportTypeRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CommentRepository commentRepository;


    @Override
    public void run(String... args) throws Exception {

        Article news1 = new Article("aaaa", "hhht", "llllllllllllllllllllll");
        Article news2 = new Article("Antras", "hhht", "rrrrrrrrrrrrrrrrrrrrrr");
        Article news3 = new Article("Trecias", "hhht", "aaaaaaaaaaaaaaaaaaaaaaa");
        Article news4 = new Article("Ketvirtas", "hhht", "wwwwwwwwwwwwwwwwwwwwww");

        articleRepository.saveAll(List.of(news1, news2, news3, news4));

        logger.info(" Article: {}", news1);
        logger.info(" Article: {}", news2);
        logger.info(" Article: {}", news3);
        logger.info(" Article: {}", news4);

        Comment comment1 = new Comment("aa", "aa");
        Comment comment2 = new Comment("bb", "bb");
        Comment comment3 = new Comment("cc", "cc");

        news1.addComment(comment1);
        news1.addComment(comment2);
        news2.addComment(comment3);


        commentRepository.saveAll(List.of(comment1, comment2, comment3));

        logger.info(" News with comment: {}", news1);
        logger.info(" News with comment: {}", news2);
        logger.info(" News with comment: {}", news3);

        SportType krepsinis = new SportType("Krepsinis");
        SportType futbolas = new SportType("Futbolas");

        sportTypeRepository.saveAll(List.of(krepsinis, futbolas));

        logger.info(" Sportype: {}", krepsinis);

        Event varzybos = new Event("varzybos", "Zalgiris vs Rytas", "arena");
        Event draugiskos = new Event("draugiskos rungtynes", "Rytas", "stadium");

        varzybos.setSportType(krepsinis);
        draugiskos.setSportType(futbolas);
        eventRepository.saveAll(List.of(varzybos, draugiskos));

        krepsinis.addEvent(varzybos);
        futbolas.addEvent(draugiskos);

        logger.info(" Event1: {}", varzybos);
        logger.info(" Event2: {}",  draugiskos);

        logger.info(" SportType: {}", krepsinis);
        logger.info(" SportType: {}",  futbolas);


    }
}
