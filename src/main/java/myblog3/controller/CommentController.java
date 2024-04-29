package myblog3.controller;

import lombok.AllArgsConstructor;
import myblog3.payload.CommentDto;
import myblog3.payload.PostWithCommentDto;
import myblog3.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/postcomments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;


    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable int postId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable int postId){

        PostWithCommentDto commentsByPostId = commentService.getCommentsByPostId(postId);

        return new ResponseEntity<>(commentsByPostId,HttpStatus.OK);
    }




}
