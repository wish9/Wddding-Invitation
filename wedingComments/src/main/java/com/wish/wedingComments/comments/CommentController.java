package com.wish.wedingComments.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "https://eunjin.site")
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public ResponseEntity<List<Comment>> showComments() {
        List<Comment> comments = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveComments(@RequestParam("name") String name, @RequestParam("comment") String comment) {
        Comment newComment = new Comment();
        newComment.setName(name);
        newComment.setComment(comment);
        commentRepository.save(newComment);

        Map<String, String> response = new HashMap<>();
        response.put("msg", "댓글이 성공적으로 저장되었습니다.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
