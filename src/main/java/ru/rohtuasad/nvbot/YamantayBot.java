package ru.rohtuasad.nvbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class YamantayBot extends AbilityBot {

  public YamantayBot(String botToken, String botUsername) {
    super(botToken, botUsername);
  }

  @Override
  public long creatorId() {
    return 0;
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
      message.setChatId(update.getMessage().getChatId().toString());
      message.setText(update.getMessage().getText());

      try {
        execute(message); // Call method to send the message
      } catch (TelegramApiException e) {
        log.error("Process message error", e);
      }
    }
  }
}
