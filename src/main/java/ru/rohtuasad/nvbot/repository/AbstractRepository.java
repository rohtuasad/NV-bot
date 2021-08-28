package ru.rohtuasad.nvbot.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AbstractRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  protected int update(Query query) {
    return namedParameterJdbcTemplate.update(query.getTemplate(), query.getParameters());
  }
}
