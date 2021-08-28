package ru.rohtuasad.nvbot.repository;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Query {
  private final Map<String, Object> parameters;
  private final String template;
}
