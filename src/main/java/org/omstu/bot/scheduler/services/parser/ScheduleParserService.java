package org.omstu.bot.scheduler.services.parser;

import java.text.MessageFormat;

import lombok.RequiredArgsConstructor;
import org.omstu.bot.scheduler.entities.ScheduleEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ScheduleParserService {

    private final RestTemplate restTemplate;

    @Value("${schedule.OMSTU}")
    private String scheduleUrl;

    public ScheduleEntity[] parse(Integer numberOfGroup, String start, String end) {
        String formatUrl = MessageFormat.format(scheduleUrl, numberOfGroup, start, end);
        ResponseEntity<ScheduleEntity[]> response = this.restTemplate.getForEntity(formatUrl, ScheduleEntity[].class);
        ScheduleEntity[] schedule = response.getBody();
        return schedule;
    }

}
