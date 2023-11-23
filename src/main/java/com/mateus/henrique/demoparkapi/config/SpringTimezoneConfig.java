package com.mateus.henrique.demoparkapi.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class SpringTimezoneConfig {
  
  @PostConstruct
  public void TimeZoneConfig() {
    TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_paulo"));
  }
}
