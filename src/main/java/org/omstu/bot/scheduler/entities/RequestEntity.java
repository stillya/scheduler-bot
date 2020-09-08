package org.omstu.bot.scheduler.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestEntity {

    Integer group;
    String firstName;
    String lastName;
    Long chatId;

}


