package edu.alieninvasion.backend.mapper;

import edu.alieninvasion.backend.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    int addNewComment(Comment comment);

    int updateComment(Comment comment);

    int deleteCommentById(@Param("cid") String cid);

    int getCommentCountByUid(@Param("uid") Long uid);

    int getCommentCount();

    List<Comment> getCommentByUid(@Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid);

    List<Comment> getAllComment(@Param("start") Integer start, @Param("count") Integer count);
}