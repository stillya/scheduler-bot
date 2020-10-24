package org.omstu.bot.scheduler.services.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.omstu.bot.scheduler.entities.RequestEntity;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.repositories.TaskRepository;
import org.omstu.bot.scheduler.services.bot.SchedulerTelegramBot;
import org.omstu.bot.scheduler.services.subscriber.ScheduleSubscribeService;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class LauncherTask implements Runnable {

    private final ScheduleSubscribeService subscribeService;
    private final TaskRepository taskRepository;
    private final SchedulerTelegramBot bot;

    @Setter
    private Long chatId;

    @Override
    public void run() {
        TaskEntity task = this.taskRepository.findByChatId(this.chatId);
        if (task.getIsFinished()) {
            return;
        }
        this.bot.sendMessage(MessageBuilder.buildMessage(this.chatId, task.getContent()));

        this.taskRepository.deleteByChatId(this.chatId);

        this.subscribeService.subscribe(RequestEntity.builder()
                .group(task.getGroup())
                .lastName(task.getLastName())
                .firstName(task.getFirstName())
                .chatId(this.chatId)
                .subGroup(task.getSubGroup())
                .build());

    }
}
