package edu.alieninvasion.backend.mapper;

import edu.alieninvasion.backend.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h1>UserService</h1>
 * This is a mapper interface interacting with MyBatis and database.
 * It deals with user comments.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@Mapper
public interface CommentMapper {
    // add a new comment
    int addNewComment(Comment comment);
    // update the existing comment
    int updateComment(Comment comment);
    // delete the comment by comment id
    int deleteCommentById(@Param("cid") String cid);
    // get the number of comments by user id
    int getCommentCountByUid(@Param("uid") Long uid);
    // get teh total number of comments
    int getCommentCount();
    // delete comments by user id
    void deleteCommentByUid(Long uid);
    // get the comments by user id
    List<Comment> getCommentByUid(@Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid);
    // get all comments in database
    List<Comment> getAllComment(@Param("start") Integer start, @Param("count") Integer count);
}