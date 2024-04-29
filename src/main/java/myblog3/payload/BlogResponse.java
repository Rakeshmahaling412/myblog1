package myblog3.payload;

import lombok.Data;

import java.util.List;

@Data
public class BlogResponse {

    private List<PostDto> dtos;

    private int pageNumbers;
    private double getElements;
    private int totalPages;

    private boolean firstPage;
    private boolean lastPage;

}
