package org.omstu.bot.scheduler.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleEntity {

    private String auditorium;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    private Date beginLesson;
    private Date date;

}
