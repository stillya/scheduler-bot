package org.omstu.bot.scheduler.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.omstu.bot.scheduler.services.bot.SchedulerTelegramBot;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerDecider;
import org.omstu.bot.scheduler.services.scheduler.LauncherTask;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class BotConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SchedulerTelegramBot schedulerTelegramBot(HandlerDecider handlerDecider) {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        return new SchedulerTelegramBot(options, handlerDecider);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LauncherTask launcherTask() {
        return new LauncherTask();
    }
}
