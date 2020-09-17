package org.omstu.bot.scheduler.services.bot.handlers.callbackhandlers;

import lombok.RequiredArgsConstructor;
import org.omstu.bot.scheduler.entities.RequestEntity;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerEventType;
import org.omstu.bot.scheduler.services.bot.intefaces.CallbackHandler;
import org.omstu.bot.scheduler.services.subscriber.ScheduleSubscribeService;
import org.omstu.bot.scheduler.utils.GroupBuilder;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
public class SubscribeCallbackHandler implements CallbackHandler {

    private final ScheduleSubscribeService scheduleSubscribeService;

    @Override
    public SendMessage handle(CallbackQuery query) {
        String[] data = query.getMessage().getText().split(",");
        RequestEntity requestEntity = RequestEntity.builder()
                .chatId(query.getMessage().getChatId())
                .firstName(query.getFrom().getFirstName())
                .lastName(query.getFrom().getLastName())
                .group(GroupBuilder.setGroup(data[1].toUpperCase()))
                .build();
        if (requestEntity.getGroup().equals(000)) {
            return MessageBuilder.buildMessage(requestEntity.getChatId(), "I cannot process your request. Your group isn't exist in system." + '\n' + "If you wanna add your group, ask @Elite_Telegram.");
        }
        return this.scheduleSubscribeService.subscribe(requestEntity);
    }

    @Override
    public HandlerEventType getType() {
        return HandlerEventType.SUBSCRIBE;
    }
}
