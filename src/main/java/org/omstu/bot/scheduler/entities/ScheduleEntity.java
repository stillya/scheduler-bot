package org.omstu.bot.scheduler.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleEntity {

    private String beginLesson;
    private String dayOfWeekString;
    private String discipline;
    private String endLesson;
    private String kindOfWork;
    private String lecturer;
    private String auditorium;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getBeginLesson() {
        return beginLesson;
    }

    public void setBeginLesson(String beginLesson) {
        this.beginLesson = beginLesson;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(String auditorium) {
        this.auditorium = auditorium;
    }

    public String getDayOfWeekString() {
        return dayOfWeekString;
    }

    public void setDayOfWeekString(String dayOfWeekString) {
        this.dayOfWeekString = dayOfWeekString;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getEndLesson() {
        return endLesson;
    }

    public void setEndLesson(String endLesson) {
        this.endLesson = endLesson;
    }

    public String getKindOfWork() {
        return kindOfWork;
    }

    public void setKindOfWork(String kindOfWork) {
        this.kindOfWork = kindOfWork;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return "------LECTURE IS COMING------" + '\n' + "Discipline: " + discipline + '\n' + "Day of Week: " +
                dayOfWeekString + '\n' + "Kind of Work: " + kindOfWork + '\n' + "Lecturer: " + lecturer + '\n' +
                "Begin of Lesson: " + beginLesson + '\n' + "End Of Lesson: " + endLesson + '\n' + "Date: " + date +
                '\n' + "Auditorium: " + auditorium + '\n' + "----------------------------------";

    }
}
