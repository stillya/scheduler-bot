package org.omstu.bot.scheduler.services.bot.handlers.messsagehandlers;

import lombok.RequiredArgsConstructor;
import org.omstu.bot.scheduler.entities.TaskEntity;
import org.omstu.bot.scheduler.repositories.TaskRepository;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerEventType;
import org.omstu.bot.scheduler.services.bot.intefaces.MessageHandler;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class UnsubscribeMessageHandler implements MessageHandler {

    private final TaskRepository taskRepository;

    @Override
    public SendMessage handle(Message message) {
        TaskEntity oldTask = this.taskRepository.findByChatId(message.getChatId());
        if (oldTask == null) {
            return MessageBuilder.buildMessage(message.getChatId(), "You're not subscribed!");
        }
        else if (oldTask.getIsFinished()) {
            return MessageBuilder.buildMessage(message.getChatId(), "You're already unsubscribed!");
        }
        oldTask.setIsFinished(true);
        this.taskRepository.save(oldTask);

        return MessageBuilder.buildMessage(message.getChatId(), "Unsubscribed!");
    }

    @Override
    public HandlerEventType getType() {
        return HandlerEventType.UNSUBSCRIBE;
    }
}
