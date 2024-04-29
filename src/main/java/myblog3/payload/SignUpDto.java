package myblog3.payload;


import lombok.Data;

@Data
public class SignUpDto {
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;
}
