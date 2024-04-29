package myblog3.payload;

import lombok.Data;
import myblog3.entity.Post;

import java.util.List;

@Data
public class PostWithCommentDto {

    private Post post;
    private List<CommentDto> commentDtos;
}
