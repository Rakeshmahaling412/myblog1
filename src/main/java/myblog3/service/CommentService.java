package myblog3.service;

import myblog3.payload.CommentDto;
import myblog3.payload.PostWithCommentDto;

public interface CommentService {
    CommentDto createComment(int postId, CommentDto commentDto);

    PostWithCommentDto getCommentsByPostId(int postId);
}
