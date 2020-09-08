package org.omstu.bot.scheduler.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MessageBuilder {

    public static SendMessage buildMessage(Long chatId, String message) {
        return new SendMessage(chatId, message);
    }
}
