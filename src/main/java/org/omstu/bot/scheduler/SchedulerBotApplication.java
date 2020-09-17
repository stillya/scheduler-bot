package org.omstu.bot.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableScheduling
public class SchedulerBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();

		SpringApplication.run(SchedulerBotApplication.class, args).start();
	}

}
