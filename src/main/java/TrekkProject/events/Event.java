package TrekkProject.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;

@Data
@Document(collection = "event")
public class Event {
    private int id;
    private String organizer;
    private String details;
    private int amount;
    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date endDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    private String contact;
    private String email;
    private String url;
}
