package team7.Certifications.exceptions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ExceptionResponseDto {

    private final String timestamp= ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    private int statusCode;
    private String error;
    private String message;
    private String path;

}
