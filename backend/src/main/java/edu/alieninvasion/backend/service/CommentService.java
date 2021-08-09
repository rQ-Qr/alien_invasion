package edu.alieninvasion.backend.service;

import edu.alieninvasion.backend.bean.Comment;
import edu.alieninvasion.backend.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class CommentService {
    CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public int addNewComment(Comment comment) {
        if(comment.getContent()==null || comment.getContent().equals("")) {
            comment.setContent("What A Cool Game!");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        comment.setEditTime(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
        if (comment.getId()==-1) {
            int i = commentMapper.addNewComment(comment);
            return i;
        }
        else {
            int i = commentMapper.updateComment(comment);
            return i;
        }
    }

    public boolean deleteCommentById(String cid) {
        int len = commentMapper.deleteCommentById(cid);
        return len==1;
    }

    public List<Comment> getAllComment(Integer page, Integer count) {
        int start = (page - 1) * count;
        return commentMapper.getAllComment(start, count);
    }

    public List<Comment> getCommentByUid(Long uid, Integer page, Integer count) {
        int start = (page - 1) * count;
        if(uid==-1) return commentMapper.getAllComment(start, count);
        else return commentMapper.getCommentByUid(start, count, uid);
    }

    public int getCommentCountByUid(Long uid) {
        if(uid==-1) return commentMapper.getCommentCount();
        return commentMapper.getCommentCountByUid(uid);
    }



}
