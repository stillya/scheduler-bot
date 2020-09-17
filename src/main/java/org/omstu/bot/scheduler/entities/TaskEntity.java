package org.omstu.bot.scheduler.entities;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskEntity {

    private Date beginLesson;
    private Long chatId;
    private String firstName;
    private String lastName;
    private Integer group;
    private String content;
    private Boolean isFinished;

}
