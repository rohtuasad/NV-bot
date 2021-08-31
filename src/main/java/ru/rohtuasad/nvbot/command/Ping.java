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
public class Ping extends BotCommand {

  private final ChatRepository chatRepository;

  public Ping(ChatRepository chatRepository) {
    super("ping", "Пингануть бойцов");
    this.chatRepository = chatRepository;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    log.info("Execute ping command");
    sendPingMessage(absSender, chat);
  }

  private void sendPingMessage(AbsSender absSender, Chat chat) {
    TgChat tgChat = chatRepository.getByTgChatId(String.valueOf(chat.getId()));
    if (tgChat.getPingList() != null) {
      String[] gangstas = tgChat.getPingList().split(" ");
      for (int i = 0; i < gangstas.length; i += 5) {
        StringBuilder answerText = new StringBuilder();
        for (int j = i; j < gangstas.length && j < i + 5; j++) {
          answerText.append(gangstas[j]).append(" ");
        }
        sendMessage(absSender, chat, answerText.toString());
      }
    } else {
      sendMessage(absSender, chat, "Не знаю кого пинговать \uD83E\uDD37\u200D♀️");
    }
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
