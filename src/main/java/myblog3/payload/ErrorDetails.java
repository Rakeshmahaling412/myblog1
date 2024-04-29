package myblog3.payload;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {

    private Date date;
    private String message;
    private String details;

    public ErrorDetails(Date date, String message, String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }
}
