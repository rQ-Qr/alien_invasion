package edu.alieninvasion.backend.controller;

import edu.alieninvasion.backend.bean.Comment;
import edu.alieninvasion.backend.bean.RespBean;
import edu.alieninvasion.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    CommentService commentService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewArticle(Comment comment) {
        System.out.println(comment.getUsername());
        int result = commentService.addNewComment(comment);
        if (result == 1) {
            System.out.println(comment.getId());
            return new RespBean("success", comment.getId() + "");
        } else {
            return new RespBean("error", "fail to modify");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable("id") String id) {
        boolean result = commentService.deleteCommentById(id);
        if (result) {
            return new RespBean("success", "Delete Success!");
        }
        return new RespBean("error", "Delete Fail!");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getUserComment(@RequestParam(value = "uid", defaultValue = "-1") Long uid, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count) {
        List<Comment> comments;
        comments = commentService.getCommentByUid(uid, page, count);
        int totalCount = commentService.getCommentCountByUid(uid);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("comments", comments);
        return map;
    }


}
