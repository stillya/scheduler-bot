package org.omstu.bot.scheduler.services.parser;

import java.text.MessageFormat;

import org.omstu.bot.scheduler.entities.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScheduleParserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("schedule.OMSTU")
    private String scheduleUrl;

    public ScheduleEntity[] parse(Long numberOfGroup, String start, String end) {
        MessageFormat.format(scheduleUrl, numberOfGroup, start, end);
        ResponseEntity<ScheduleEntity[]> response = restTemplate.getForEntity(scheduleUrl, ScheduleEntity[].class);
        ScheduleEntity[] schedule = response.getBody();
        return schedule;
    }

}
