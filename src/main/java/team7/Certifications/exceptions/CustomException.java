package team7.Certifications.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
public class CustomException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    public CustomException(HttpStatus httpStatus)
    {
        this(httpStatus,null,null);
    }

    public CustomException(HttpStatus httpStatus,String reason)
    {
        this(httpStatus,reason,null);
    }

    public CustomException(HttpStatus httpStatus,String reason,Throwable cause)
    {
        super(reason,cause);
        this.httpStatus=httpStatus;
    }

}
