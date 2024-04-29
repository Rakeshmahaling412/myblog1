package myblog3.service;

import myblog3.payload.BlogResponse;
import myblog3.payload.PostDto;
import org.springframework.stereotype.Service;

@Service
public interface PostService {


    PostDto createPost(PostDto postDto);

    void deleteById(int id);

    BlogResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getAlPostById(int postId);
}
