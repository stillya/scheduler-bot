package org.omstu.bot.scheduler.services.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import org.omstu.bot.scheduler.entities.ScheduleEntity;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.services.parser.ScheduleParserService;
import org.omstu.bot.scheduler.utils.DateUtil;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LectureFinderService {

    ScheduleParserService parser;

    public TaskEntity find(Integer group) {
        try {
            ScheduleEntity[] schedules = this.findNearestLectures(group);
            for (ScheduleEntity schedule : schedules) {
                if (DateUtil.fromString(schedule.getDate(), schedule.getBeginLesson())
                        .after(Calendar.getInstance().getTime())) {
                    return TaskEntity.builder()
                            .beginLesson(DateUtil.fromString(schedule.getDate(), schedule.getBeginLesson()))
                            .content(schedule.toString())
                            .build();
                }
            }
            //TODO: think about situation without scheduling
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private ScheduleEntity[] findNearestLectures(Integer group) {
        try {
            int size;
            DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String date = dateFormat.format(new Date());
            while (true) {
                ScheduleEntity[] lectures = this.parser.parse(group, date, date);
                size = lectures.length;
                if (DateUtil.fromString(lectures[size - 1].getDate(), lectures[size - 1].getBeginLesson())
                        .before(new Date())) {
                    size = 0;
                }
                date = dateFormat.format(DateUtil.addDays(new Date(), 1));
                if (size > 0) {
                    return lectures;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
