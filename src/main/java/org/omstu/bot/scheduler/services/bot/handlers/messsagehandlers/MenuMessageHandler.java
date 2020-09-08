package org.omstu.bot.scheduler.services.bot.handlers.messsagehandlers;

import java.util.ArrayList;
import java.util.List;

import org.omstu.bot.scheduler.services.bot.implementation.HandlerEventType;
import org.omstu.bot.scheduler.services.bot.intefaces.MessageHandler;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@Service
public class MenuMessageHandler implements MessageHandler {

    @Override
    public HandlerEventType getType() {
        return HandlerEventType.MENU;
    }

    @Override
    public SendMessage handle(Message message) {
        ReplyKeyboardMarkup replyKeyboardMarkup = this.getMenu();
        SendMessage mainMenuMessage = this.messageWithMenu(message.getChatId(), message.getText(), replyKeyboardMarkup);

        return mainMenuMessage;
    }

    private ReplyKeyboardMarkup getMenu() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow subscribeButton = new KeyboardRow();
        KeyboardRow describeButton = new KeyboardRow();
        subscribeButton.add(new KeyboardButton("Subscribe"));
        describeButton.add(new KeyboardButton("Describe"));
        keyboard.add(subscribeButton);
        keyboard.add(describeButton);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    private SendMessage messageWithMenu(Long chatId, String textMessage, ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage message = MessageBuilder.buildMessage(chatId, textMessage);
        message.enableMarkdown(true);
        if (replyKeyboardMarkup != null) {
            message.setReplyMarkup(replyKeyboardMarkup);
        }
        return message;
    }
}