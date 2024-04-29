package myblog3.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class CommentDto {
    private int id;
    private String name;
    private String message;
}
