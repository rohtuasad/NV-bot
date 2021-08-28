package ru.rohtuasad.nvbot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.rohtuasad.nvbot.entity.TgChat;
import ru.rohtuasad.nvbot.repository.ChatRepository;

@Slf4j
@Component
public class SetPing extends BotCommand {

  private final ChatRepository chatRepository;

  public SetPing(ChatRepository chatRepository) {
    super("setping", "Пингануть бойцов");
    this.chatRepository = chatRepository;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    TgChat tgChat = new TgChat(chat.getId().toString(), String.join(" ", strings),
        "private".equals(chat.getType()) ? chat.getUserName() : chat.getTitle());
    chatRepository.update(tgChat);
    sendMessage(absSender, chat, "Запомнил");
  }

  private void sendMessage(AbsSender absSender, Chat chat, String answerText) {
    SendMessage answer = new SendMessage();
    answer.setChatId(chat.getId().toString());
    answer.setText(answerText);

    try {
      absSender.execute(answer);
    } catch (TelegramApiException e) {
      log.error("Error while sending message", e);
    }
  }
}
