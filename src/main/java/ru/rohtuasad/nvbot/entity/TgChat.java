package ru.rohtuasad.nvbot.entity;

import lombok.Data;

@Data
public class TgChat {
  private final String tgChatId;
  private final String pingList;
  private final String tgChatName;
}
