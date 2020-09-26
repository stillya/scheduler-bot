package org.omstu.bot.scheduler.services.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.omstu.bot.scheduler.entities.ScheduleEntity;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.services.parser.ScheduleParserService;
import org.omstu.bot.scheduler.utils.DateUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureFinderService {

    private final ScheduleParserService parser;

    public TaskEntity find(Integer group, String subGroup) {
        try {
            List<ScheduleEntity> schedules;
            if (subGroup != null) {
                schedules = this.findLecturesBySubGroup(this.findNearestLectures(group), subGroup);
            } else {
                schedules = Arrays.stream(this.findNearestLectures(group)).collect(Collectors.toList());
            }
            for (ScheduleEntity schedule : schedules) {
                if (DateUtil.fromString(schedule.getDate(), schedule.getBeginLesson())
                        .after(DateUtil.addMinutes(new Date(), 20))) {
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
            Date date = new Date();
            while (true) {
                ScheduleEntity[] lectures = this.parser.parse(group, dateFormat.format(date), dateFormat.format(date));
                size = lectures.length;
                if (size != 0 && DateUtil.fromString(lectures[size - 1].getDate(), lectures[size - 1].getBeginLesson())
                        .before(DateUtil.addMinutes(new Date(), 20))) {
                    size = 0;
                }
                date = DateUtil.addDays(date, 1);
                if (size > 0) {
                    return lectures;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private List<ScheduleEntity> findLecturesBySubGroup(ScheduleEntity[] schedule, String subGroup) {
        List<ScheduleEntity> result = new ArrayList<>();
        for (ScheduleEntity s : schedule) {
            if (s.getSubGroup() == null || s.getSubGroup().split("/")[1].equals(subGroup)) {
                result.add(s);
            }
        }
        return result;
    }
}
