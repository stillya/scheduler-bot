package org.omstu.bot.scheduler.services.subscriber;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.omstu.bot.scheduler.entities.RequestEntity;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.repositories.TaskRepository;
import org.omstu.bot.scheduler.services.scheduler.LauncherTask;
import org.omstu.bot.scheduler.services.scheduler.LectureFinderService;
import org.omstu.bot.scheduler.utils.CronUtil;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class ScheduleSubscribeService {

    private final LectureFinderService lectureFinder;
    private final TaskRepository taskRepository;
    private final TaskScheduler taskScheduler;

    private boolean initialized = false;
    private boolean contextStarted = false;

    @Lookup("launcherTask")
    public LauncherTask getLauncherTask() {
        return null;
    }

    @EventListener(classes = {ContextStartedEvent.class})
    public void handleContextStartEvent() {
        this.contextStarted = true;
    }

    @EventListener(classes = {ContextStoppedEvent.class})
    public void handleContextStoppedEvent() {
        this.contextStarted = false;
    }

    @Scheduled(fixedDelay = 100000)
    public void scheduleInitialTasks() {
        if (!initialized && contextStarted) {
            List<TaskEntity> tasks = this.taskRepository.findAll();
            for (TaskEntity t : tasks) {
                LauncherTask launcherTask = this.getLauncherTask();
                launcherTask.setChatId(t.getChatId());
                this.taskScheduler.schedule(launcherTask, CronUtil.toCronTrigger(t.getBeginLesson()));
            }
            this.initialized = true;
        }
    }

    public SendMessage subscribe(RequestEntity request) {
        TaskEntity task = this.lectureFinder.find(request.getGroup());
        task.setChatId(request.getChatId());
        task.setFirstName(request.getFirstName());
        task.setLastName(request.getLastName());
        task.setGroup(request.getGroup());
        task.setIsFinished(false);

        TaskEntity oldTask = this.taskRepository.findByChatId(task.getChatId());

        if (oldTask != null && oldTask.getIsFinished()) {
            return MessageBuilder.buildMessage(task.getChatId(), "You're already subscribe!");
        }
        this.taskRepository.save(task);

        LauncherTask launcherTask = this.getLauncherTask();
        launcherTask.setChatId(task.getChatId());
        this.taskScheduler.schedule(launcherTask, CronUtil.toCronTrigger(task.getBeginLesson()));

        return MessageBuilder.buildMessage(task.getChatId(), "Subscribed!");
    }

}
