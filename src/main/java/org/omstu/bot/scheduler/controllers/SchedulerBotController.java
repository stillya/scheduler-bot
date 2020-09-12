package org.omstu.bot.scheduler.controllers;

import lombok.AllArgsConstructor;
import org.omstu.bot.scheduler.entities.ScheduleEntity;
import org.omstu.bot.scheduler.services.bot.SchedulerTelegramBot;
import org.omstu.bot.scheduler.services.parser.ScheduleParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
public class SchedulerBotController {

    SchedulerTelegramBot schedulerTelegramBot;
    ScheduleParser parser;

    @GetMapping(path = "/webhook", produces = "application/json;charset=UTF-8")
    public ScheduleEntity[] onUpdateReceived() {
        return this.parser.parse();
    }
}

/*
@PostMapping(path = "/webhook")
    public BotApiMethod onUpdateReceived(@RequestBody Update update) {
        return this.schedulerTelegramBot.onWebhookUpdateReceived(update);
    }
    ?
 */
