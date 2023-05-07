package com.baigiamasis.sportstream;

import com.baigiamasis.sportstream.article.Article;
import com.baigiamasis.sportstream.article.ArticleRepository;
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

        Article news1 = new Article("Pirmas", "https://media.istockphoto.com/id/1141191007/vector/sports-" +
                "set-of-athletes-of-various-sports-disciplines-isolated-vector-silhouettes-run-soccer.jpg?s=612x612&w=" +
                "0&k=20&c=SEabW4SHZ7blMHJPxZNSTl_anOMHO3whQI7HIMxFpSg=", "Lorem Ipsum is simply dummy text of the" +
                " printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since " +
                "the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen" +
                " book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets" +
                " containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus " +
                "PageMaker including versions of Lorem Ipsum.");
        Article news2 = new Article("Antras", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS" +
                "guO0RVwYgJYtIw3ZtDHU9jtjI4QoeiKciCA&usqp=CAU", "Lorem Ipsum is simply dummy text of the" +
                " printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since " +
                "the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen" +
                " book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets" +
                " containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus " +
                "PageMaker including versions of Lorem Ipsum.");
        Article news3 = new Article("Trecias", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcDQ" +
                "bdfQepfnqBSeJKxbFKRrS6G_YJxu0vhw&usqp=CAU", "Lorem Ipsum is simply dummy text of the" +
                " printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since " +
                "the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen" +
                " book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets" +
                " containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus " +
                "PageMaker including versions of Lorem Ipsum.");
        Article news4 = new Article("Ketvirtas", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ" +
                "Oz5k_wk7LPEa_E3ehY25d0BoNmK7dzkm6FQ&usqp=CAU" +
                "sports-set-of-athletes-of-various-sports-disciplines-isolated-vector-silhouettes-run-soccer.jpg?" +
                "s=612x612&w=0&k=20&c=SEabW4SHZ7blMHJPxZNSTl_anOMHO3whQI7HIMxFpSg=","Lorem Ipsum " +
                "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's " +
                "the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen" +
                " book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets" +
                " containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus " +
                "PageMaker including versions of Lorem Ipsum.");

        articleRepository.saveAll(List.of(news1, news2, news3, news4));

        logger.info(" Article: {}", news1);
        logger.info(" Article: {}", news2);
        logger.info(" Article: {}", news3);
        logger.info(" Article: {}", news4);

        Comment comment1 = new Comment("Jonas", "It has survived not only five centuries, but also the " +
                "leap into electronic typesetting remaining essentially unchanged.");
        Comment comment2 = new Comment("Antanas", "It has survived not only five centuries, but also the " +
                "leap into electronic typesetting remaining essentially unchanged.");
        Comment comment3 = new Comment("Petras", "It has survived not only five centuries, but also the " +
                "leap into electronic typesetting remaining essentially unchanged.");

        news1.addComment(comment1);
        news1.addComment(comment2);
        news2.addComment(comment3);

        commentRepository.saveAll(List.of(comment1, comment2, comment3));

        logger.info(" News with comment: {}", news1);
        logger.info(" News with comment: {}", news2);
        logger.info(" News with comment: {}", news3);

        SportType krepsinis = new SportType("Krepšinis");
        SportType futbolas = new SportType("Futbolas");

        sportTypeRepository.saveAll(List.of(krepsinis, futbolas));

        logger.info(" Sportype: {}", krepsinis);

        Event varzybos = new Event("Varžybos", "Žalgiris", " Rytas", "Kauno arena");
        Event draugiskos = new Event("Draugiskos varžybos", "Rytas", "Lietkabelis",
                "Vilnius arena");

        varzybos.setSportType(krepsinis);
        draugiskos.setSportType(futbolas);
        eventRepository.saveAll(List.of(varzybos, draugiskos));

        krepsinis.addEvent(varzybos);
        futbolas.addEvent(draugiskos);

        logger.info(" Event1: {}", varzybos);
        logger.info(" Event2: {}", draugiskos);

        logger.info(" SportType: {}", krepsinis);
        logger.info(" SportType: {}", futbolas);

    }
}
