package com.maximbuza.appaston.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@org.springframework.context.annotation.Configuration // обозначаем как конфигурацию
@ComponentScan(basePackages = "com.maximbuza.appaston") // где нужно искать компоненты
@EnableWebMvc // включаем MVC для обработки запросов HTTP
public class Configuration {
}
