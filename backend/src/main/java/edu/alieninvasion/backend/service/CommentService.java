package edu.alieninvasion.backend.service;

import edu.alieninvasion.backend.bean.Comment;
import edu.alieninvasion.backend.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <h1>CommentService</h1>
 * This is a service class implementing the CRUD of comments.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@Service
@Transactional
public class CommentService {
    CommentMapper commentMapper;
    // constructor of the class
    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * This method is used to add a new comment.
     *
     * @param comment the comment needed to add.
     * @return the number of changed records
     */
    public int addNewComment(Comment comment) {
        // if no comment, create a new comment content.
        if(comment.getContent()==null || comment.getContent().equals("")) {
            comment.setContent("What A Cool Game!");
        }
        // format the edit time.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // set the edit time
        comment.setEditTime(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
        int i;
        // if no comment id, it's a new comment
        if(comment.getId()==-1) {
            i = commentMapper.addNewComment(comment);
        }
        // else, update the existing comment
        else {
            i = commentMapper.updateComment(comment);
        }
        return i;
    }

    /**
     * This method is used to delete a comment.
     *
     * @param cid the comment id.
     * @return check if delete the comment
     */
    public boolean deleteCommentById(String cid) {
        int len = commentMapper.deleteCommentById(cid);
        return len==1;
    }

    /**
     * This method is used to delete a comment by user id.
     *
     * @param uid the id of user.
     */
    public void deleteCommentByUid(Long uid) {
        commentMapper.deleteCommentByUid(uid);
    }

    /**
     * This method is used to get all comments.
     *
     * @param page the page number of comments
     * @param count the number of comments
     * @return the list of comments
     */
    public List<Comment> getAllComment(Integer page, Integer count) {
        int start = (page - 1) * count;
        return commentMapper.getAllComment(start, count);
    }

    /**
     * This method is used to get the comments of one user.
     *
     * @param uid the user id
     * @param page the page number of comments
     * @param count the number of comments
     * @return the list of comments
     */
    public List<Comment> getCommentByUid(Long uid, Integer page, Integer count) {
        int start = (page - 1) * count;
        // return all comments
        if(uid==-1) return commentMapper.getAllComment(start, count);
        // return comments of one user
        else return commentMapper.getCommentByUid(start, count, uid);
    }


    /**
     * This method is used to get the numbers of comments of one user.
     *
     * @param uid the page number of comments
     * @return the number of comments
     */
    public int getCommentCountByUid(Long uid) {
        if(uid==-1) return commentMapper.getCommentCount();
        return commentMapper.getCommentCountByUid(uid);
    }



}
