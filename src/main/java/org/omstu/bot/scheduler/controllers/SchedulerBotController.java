package org.omstu.bot.scheduler.controllers;

import lombok.RequiredArgsConstructor;
import org.omstu.bot.scheduler.services.bot.SchedulerTelegramBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class SchedulerBotController {

    private final SchedulerTelegramBot schedulerTelegramBot;

    @PostMapping(path = "/webhook")
    public BotApiMethod onUpdateReceived(@RequestBody Update update) {
        return this.schedulerTelegramBot.onWebhookUpdateReceived(update);
    }
}
