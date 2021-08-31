package ru.rohtuasad.nvbot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TgChat {
  private String tgChatId;
  private String pingList;
  private String tgChatName;
}
