package org.omstu.bot.scheduler.services.subscriber;

import lombok.AllArgsConstructor;
import org.omstu.bot.scheduler.entities.RequestEntity;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.repositories.TaskRepository;
import org.omstu.bot.scheduler.services.scheduler.LauncherTask;
import org.omstu.bot.scheduler.services.scheduler.LectureFinderService;
import org.omstu.bot.scheduler.utils.CronUtil;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@AllArgsConstructor
public class ScheduleSubscribeService {

    private LectureFinderService lectureFinder;
    private TaskRepository taskRepository;
    private final TaskScheduler taskScheduler;

    @Lookup("launcherTask")
    public LauncherTask getLauncherTask() {
        return null;
    }

    public SendMessage subscribe(RequestEntity request) {
        TaskEntity task = this.lectureFinder.find(request.getGroup());
        task.setChatId(request.getChatId());
        task.setFirstName(request.getFirstName());
        task.setLastName(request.getLastName());
        task.setGroup(request.getGroup());
        task.setIsFinished(false);

        if (this.taskRepository.findByChatId(task.getChatId()).getIsFinished()) {
            return MessageBuilder.buildMessage(task.getChatId(), "You're already subscribe");
        }
        this.taskRepository.save(task);

        LauncherTask launcherTask = this.getLauncherTask();
        launcherTask.setChatId(task.getChatId());
        this.taskScheduler.schedule(launcherTask, CronUtil.toCronTrigger(task.getBeginLesson()));

        return MessageBuilder.buildMessage(task.getChatId(), "Subscribe!");
    }

}
