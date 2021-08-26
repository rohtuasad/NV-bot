package ru.rohtuasad.nvbot.configuration;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.rohtuasad.nvbot.CommandHandler;

@Slf4j
@Configuration
public class AppConfig {
  private final CommandHandler commandHandler;

  public AppConfig(CommandHandler commandHandler) {
    this.commandHandler = commandHandler;
  }

  @PostConstruct
  public void init() {
    try {
      TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
      telegramBotsApi.registerBot(this.commandHandler);
    } catch (TelegramApiException e) {
      log.error("Bot initialize error", e);
    }
  }
}
