package edu.alieninvasion.backend.controller;

import edu.alieninvasion.backend.bean.Comment;
import edu.alieninvasion.backend.bean.RespBean;
import edu.alieninvasion.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <h1>CommentController</h1>
 * This is a controller class to deal with request about comments.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    CommentService commentService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    // constructor of the class
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * This method is used to add a new comment
     *
     * @param comment the comment instance
     * @return the response bean
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewComment(Comment comment) {
        int result = commentService.addNewComment(comment);
        // success
        if (result == 1) {
            return new RespBean("success", comment.getId() + "");
        } else {
            return new RespBean("error", "fail to modify");
        }
    }

    /**
     * This method is used to delete a comment by id
     *
     * @param id the comment id
     * @return the response bean
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable("id") String id) {
        boolean result = commentService.deleteCommentById(id);
        // success
        if (result) {
            return new RespBean("success", "Delete Success!");
        }
        return new RespBean("error", "Delete Fail!");
    }

    /**
     * This method is used to get comments of one user or all users
     *
     * @param uid the id of user
     * @param page the page number of comments
     * @param count the number of comments
     * @return the response bean
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getUserComment(@RequestParam(value = "uid", defaultValue = "-1") Long uid, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count) {
        List<Comment> comments;
        // list of comments
        comments = commentService.getCommentByUid(uid, page, count);
        // total number of all comments
        int totalCount = commentService.getCommentCountByUid(uid);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("comments", comments);
        return map;
    }


}
