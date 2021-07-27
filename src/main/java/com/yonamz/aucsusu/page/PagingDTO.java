package page;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class PagingDTO {

    private Long id;
    private String title;
    private String createdBy;
    private Timestamp createdTime;

    public PagingDTO(Long id, String title, String createdBy, Timestamp createdTime){
        this.id = id;
        this.title = title;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }
}
