package com.example.gamedia;

import com.example.gamedia.utils.CoinGeckoTsvDataMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class GamediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamediaApplication.class, args);
        CoinGeckoTsvDataMapper.tsvr(new File("CoinGeckoTokenList.tsv"));
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "|{}[]"));
        return factory;
    }

}
