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
public class IsSubscribeMessageHandler implements MessageHandler {

    private final TaskRepository taskRepository;

    @Override
    public SendMessage handle(Message message) {
        TaskEntity task = this.taskRepository.findByChatId(message.getChatId());
        if (task == null) {
            return MessageBuilder.buildMessage(message.getChatId(), "You're not subscriber");
        }
        if (task.getIsFinished().equals(false)) {
            return MessageBuilder.buildMessage(message.getChatId(), "You're not subscriber");
        } else {
            return MessageBuilder.buildMessage(message.getChatId(), "You're subscriber");
        }
    }

    @Override
    public HandlerEventType getType() {
        return HandlerEventType.ISSUBSRIBE;
    }
}
