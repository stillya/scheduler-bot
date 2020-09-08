package org.omstu.bot.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class SchedulerBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();

		SpringApplication.run(SchedulerBotApplication.class, args);
	}

}
