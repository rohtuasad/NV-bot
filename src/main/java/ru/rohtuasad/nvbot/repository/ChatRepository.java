package ru.rohtuasad.nvbot.repository;

import java.util.Map;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.rohtuasad.nvbot.entity.TgChat;

@Repository
public class ChatRepository extends AbstractRepository {

  public ChatRepository(
      NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    super(namedParameterJdbcTemplate);
  }

  public int update(TgChat chat) {
    Query query = new Query(Map.of("tg_chat_id", chat.getTgChatId(),
        "ping_list", chat.getPingList(),
        "tg_chat_name", chat.getTgChatName()),
        "INSERT INTO wasteland.chat (tg_chat_id, ping_list, tg_chat_name)\n"
            + "VALUES(:tg_chat_id, :ping_list, :tg_chat_name) \n"
            + "ON CONFLICT (tg_chat_id) \n"
            + "DO UPDATE SET ping_list = EXCLUDED.ping_list, tg_chat_name = EXCLUDED.tg_chat_name;");
    return super.update(query);
  }
}
