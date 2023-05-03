package com.baigiamasis.sportstream.comments;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository repository) {
        this.commentRepository = repository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Integer id) {
        return commentRepository.findById((Integer) id).get();
    }

    public List<Comment> getAllComments() {

        return commentRepository.findAll();
    }

    public void deleteComment(Integer id) {

        commentRepository.deleteById(id);
    }


}
