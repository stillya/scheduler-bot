package org.omstu.bot.scheduler.services.bot.handlers.messsagehandlers;

import lombok.RequiredArgsConstructor;
import org.omstu.bot.scheduler.entities.RequestEntity;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerEventType;
import org.omstu.bot.scheduler.services.bot.intefaces.MessageHandler;
import org.omstu.bot.scheduler.services.subscriber.ScheduleSubscribeService;
import org.omstu.bot.scheduler.utils.GroupBuilder;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class SubscribeMessageHandler implements MessageHandler {

    private final ScheduleSubscribeService scheduleSubscribeService;

    // TODO: Add exception handling
    @Override
    public SendMessage handle(Message message) {
        try {
            String[] data = message.getText().split(",");
            RequestEntity requestEntity = RequestEntity.builder()
                    .chatId(message.getChat().getId())
                    .firstName(message.getFrom().getFirstName())
                    .lastName(message.getFrom().getLastName())
                    .group(GroupBuilder.setGroup(data[1].trim().toUpperCase()))
                    .build();
            if (requestEntity.getGroup().equals(000)) {
                return MessageBuilder.buildMessage(requestEntity.getChatId(),
                        "I cannot process your request. Your group isn't exist in system." + '\n' + "If you wanna add your group, ask @Elite_Telegram.");
            }
            return this.scheduleSubscribeService.subscribe(requestEntity);
        } catch (Exception e) {
            return MessageBuilder.buildMessage(message.getChatId(), "I can't handle your message");
        }
    }

    @Override
    public HandlerEventType getType() {
        return HandlerEventType.SUBSCRIBE;
    }
}
