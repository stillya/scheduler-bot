package org.omstu.bot.scheduler.services.bot.handlers;

import org.omstu.bot.scheduler.services.bot.implementation.HandlerType;
import org.omstu.bot.scheduler.services.bot.intefaces.UpdateHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageUpdateHandler implements UpdateHandler {

    @Override
    public HandlerType getHandlerType() {
        return HandlerType.MESSAGE_HANDLER;
    }

    @Override
    public SendMessage handle(Update update) {
        return null;
    }
}
