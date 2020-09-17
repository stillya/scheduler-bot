package org.omstu.bot.scheduler.services.scheduler;

import lombok.Setter;
import org.omstu.bot.scheduler.entities.RequestEntity;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.repositories.TaskRepository;
import org.omstu.bot.scheduler.services.bot.SchedulerTelegramBot;
import org.omstu.bot.scheduler.services.subscriber.ScheduleSubscribeService;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class LauncherTask implements Runnable {

    @Autowired
    private ScheduleSubscribeService subscribeService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SchedulerTelegramBot bot;

    @Setter
    private Long chatId;

    @Override
    public void run() {
        TaskEntity task = this.taskRepository.findByChatId(chatId);
        if (task.getIsFinished()) {
            return;
        }
        this.bot.sendMessage(MessageBuilder.buildMessage(chatId, task.getContent()));

        this.subscribeService.subscribe(RequestEntity.builder()
                .group(task.getGroup())
                .lastName(task.getLastName())
                .firstName(task.getFirstName())
                .chatId(this.chatId)
                .build());

    }
}
