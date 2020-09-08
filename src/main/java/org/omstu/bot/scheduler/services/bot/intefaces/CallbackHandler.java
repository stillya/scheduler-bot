package org.omstu.bot.scheduler.services.bot.intefaces;

import org.omstu.bot.scheduler.services.bot.implementation.HandlerEventType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackHandler {
    SendMessage handle(CallbackQuery query);
    HandlerEventType getType();
}
