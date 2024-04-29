package myblog3.repository;

import myblog3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> getAllCommentsByPostId(int postId);
}
