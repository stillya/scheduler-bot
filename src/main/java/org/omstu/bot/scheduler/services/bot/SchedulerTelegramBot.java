package org.omstu.bot.scheduler.services.bot;

import java.util.Optional;

import org.omstu.bot.scheduler.services.bot.implementation.HandlerDecider;
import org.omstu.bot.scheduler.services.bot.intefaces.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SchedulerTelegramBot extends TelegramWebhookBot {

    private HandlerDecider handlerDecider;

    @Value("${schedulerBot.name}")
    private String botUsername;
    @Value("${schedulerBot.token}")
    private String botToken;
    @Value("${schedule.OMSTU}")
    private String botPath;

    public SchedulerTelegramBot(DefaultBotOptions defaultBotOptions, HandlerDecider handlerDecider) {
        super(defaultBotOptions);
        this.handlerDecider = handlerDecider;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        Optional<UpdateHandler> updateHandler = this.handlerDecider.decide(update);
        return updateHandler.get().handle(update);
    }

    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public String getBotPath() {
        return this.botPath;
    }
}
