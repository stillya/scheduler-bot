package org.omstu.bot.scheduler.services.scheduler;

import lombok.Setter;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.repositories.TaskRepository;
import org.omstu.bot.scheduler.services.bot.SchedulerTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;

public class LauncherTask implements Runnable {

    @Autowired
    private LectureFinderService lectureFinder;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    SchedulerTelegramBot bot;

    @Setter
    private Long chatId;

    @Override
    public void run() {
        TaskEntity task = this.taskRepository.findByChatId(chatId);
        if (task.getIsFinished()) {
            return;
        }

    }
}
