package org.omstu.bot.scheduler.services.bot.intefaces;

import org.omstu.bot.scheduler.services.bot.implementation.HandlerType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

    SendMessage handle(Update update);

    HandlerType getHandlerType();
}
