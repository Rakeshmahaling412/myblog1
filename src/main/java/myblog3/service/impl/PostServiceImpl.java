package myblog3.service.impl;

import myblog3.entity.Post;
import myblog3.exception.ResourceNotFound;
import myblog3.payload.BlogResponse;
import myblog3.payload.PostDto;
import myblog3.repository.PostRepository;
import myblog3.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper)
    {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post saved = postRepository.save(post);

        PostDto dto = mapToDto(saved);


        return dto;

    }

    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);

    }

    @Override
    public BlogResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize,sort);
        Page<Post> all = postRepository.findAll(pageRequest);
        List<Post> posts = all.getContent();
        List<PostDto> dto = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setDtos(dto);
        blogResponse.setFirstPage(all.isFirst());
        blogResponse.setGetElements(all.getTotalElements());
        blogResponse.setLastPage(all.isLast());
        blogResponse.setTotalPages(all.getTotalPages());
        blogResponse.setPageNumbers(all.getTotalPages());


        return blogResponse;
    }

    @Override
    public PostDto getAlPostById(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post  Not Found With Id" + postId));
        return mapToDto(post);

    }

    Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);

        return post;

    }

    PostDto mapToDto(Post post){

        PostDto dto = modelMapper.map(post, PostDto.class);


        return dto;


    }


}

