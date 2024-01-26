package com.maximbuza.appaston.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration // обозначаем как конфигурацию
@ComponentScan(basePackages = "com.maximbuza.appaston") // где нужно искать компоненты
@EnableWebMvc // включаем MVC для обработки запросов HTTP
public class MyConfiguration {

}
