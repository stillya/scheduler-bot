package org.omstu.bot.scheduler.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestEntity {

    private Integer group;
    private String firstName;
    private String lastName;
    private Long chatId;
    private String subGroup;

}


