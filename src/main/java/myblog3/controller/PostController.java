package myblog3.controller;

import myblog3.payload.BlogResponse;
import myblog3.payload.PostDto;
import myblog3.service.PostService;
import myblog3.service.impl.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable int id){
        postService.deleteById(id);

        return  new ResponseEntity<>("Record Deleted",HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<BlogResponse> getAllPosts(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue = "5",required = false)int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        BlogResponse allPost = postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allPost,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllCommentsById(@PathVariable int id){
        PostDto dto = postService.getAlPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }







}
