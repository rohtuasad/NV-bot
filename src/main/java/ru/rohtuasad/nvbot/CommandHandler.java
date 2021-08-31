package ru.rohtuasad.nvbot;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rohtuasad.nvbot.command.Ping;
import ru.rohtuasad.nvbot.command.SetPing;

@Slf4j
@Component
public class CommandHandler extends TelegramLongPollingCommandBot {

  @Value("${bot.token}")
  private String botToken;

  @Value("${bot.username}")
  private String botUsername;

  private final SetPing setPing;
  private final Ping ping;

  public CommandHandler(SetPing setPing, Ping ping) {
    this.setPing = setPing;
    this.ping = ping;
  }

  @PostConstruct
  public void init() {
    register(setPing);
    register(ping);
  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public void processNonCommandUpdate(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      SendMessage message = new SendMessage();
      message.setChatId(update.getMessage().getChatId().toString());
      message.setText(update.getMessage().getText());
      try {
        execute(message);
      } catch (TelegramApiException e) {
        log.error("Process message error", e);
      }
    }
  }

  @Override
  public String getBotToken() {
    return botToken;
  }
}
