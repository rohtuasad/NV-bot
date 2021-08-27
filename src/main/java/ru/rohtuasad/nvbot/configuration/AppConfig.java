package ru.rohtuasad.nvbot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rohtuasad.nvbot.YamantayBot;

@Slf4j
@Configuration
public class AppConfig {
  @Value("${bot.token}")
  private String botToken;

  @Value("${bot.username}")
  private String botUsername;

  @Bean
  YamantayBot yamantayBot() {
    return new YamantayBot(botToken, botUsername);
  }
}
