package org.omstu.bot.scheduler.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.omstu.bot.scheduler.services.bot.SchedulerTelegramBot;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerDecider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
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
}
