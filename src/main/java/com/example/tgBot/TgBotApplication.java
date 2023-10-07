package com.example.tgBot;

import com.example.tgBot.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
public class TgBotApplication {
	@Autowired
	public TgBotApplication(Dispatcher dispatcher, UserSessionService userSessionService) {
		TgBotApplication.dispatcher = dispatcher;
		TgBotApplication.userSessionService = userSessionService;
	}

	private static Dispatcher dispatcher;
	private static UserSessionService userSessionService;
	public static final Logger logger = LoggerFactory.getLogger(TgBotApplication.class);
	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(TgBotApplication.class,args);
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		try {
			logger.info("Registering bot...");
			telegramBotsApi.registerBot(new TgBot(dispatcher,userSessionService));
		} catch (TelegramApiRequestException e) {
			logger.error("Failed to register bot(check internet connection / bot token or make sure only one instance of bot is running).", e);
		}
		logger.info("Telegram bot is ready to accept updates from user......");
	}
}
