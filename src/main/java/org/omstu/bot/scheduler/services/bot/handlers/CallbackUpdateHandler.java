package org.omstu.bot.scheduler.services.bot.handlers;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerEventType;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerType;
import org.omstu.bot.scheduler.services.bot.intefaces.CallbackHandler;
import org.omstu.bot.scheduler.services.bot.intefaces.UpdateHandler;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@AllArgsConstructor
public class CallbackUpdateHandler implements UpdateHandler {

    List<CallbackHandler> handlers;

    @Override
    public HandlerType getHandlerType() {
        return HandlerType.CALLBACK_HANDLER;
    }

    @Override
    public SendMessage handle(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();

        HandlerEventType usersQueryType = HandlerEventType.valueOf(callbackQuery.getData().split("\\|")[0]);

        Optional<CallbackHandler> queryHandler = handlers.stream().
                filter(handler -> handler.getType().equals(usersQueryType)).findFirst();

        return queryHandler.map(handler -> handler.handle(callbackQuery)).
                orElse(MessageBuilder.buildMessage(callbackQuery.getMessage().getChatId(), "reply.query.failed"));
    }
}
