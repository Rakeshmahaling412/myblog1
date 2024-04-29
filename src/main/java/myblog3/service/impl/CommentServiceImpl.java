package myblog3.service.impl;

import lombok.AllArgsConstructor;
import myblog3.entity.Comment;
import myblog3.entity.Post;
import myblog3.exception.ResourceNotFound;
import myblog3.payload.CommentDto;
import myblog3.payload.PostWithCommentDto;
import myblog3.repository.CommentRepository;
import myblog3.repository.PostRepository;
import myblog3.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl  implements CommentService {
    private ModelMapper modelMapper;
    private PostRepository postRepository;
    private CommentRepository commentRepository;



    @Override
    public CommentDto createComment(int postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).get();
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);
       return  mapToDto(save);

    }

    @Override
    public PostWithCommentDto getCommentsByPostId(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post Not Found With id:" + postId));

        List<Comment> allCommentsByPostId = commentRepository.getAllCommentsByPostId(postId);
        List<CommentDto> dto = allCommentsByPostId.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        PostWithCommentDto postWithCommentDto = new PostWithCommentDto();
        postWithCommentDto.setPost(post);
        postWithCommentDto.setCommentDtos(dto);
        return postWithCommentDto;
    }

    Comment mapToEntity(CommentDto commentDto){

        return modelMapper.map(commentDto,Comment.class);
    }
    CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment,CommentDto.class);
    }
}
