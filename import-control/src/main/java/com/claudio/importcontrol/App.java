package com.claudio.importcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    String port = dotenv.get("PORT");

    if (port == null) {
        System.out.println("ERRO: O .env não foi lido ou a variável PORT não existe nele.");
    } else {
        System.out.println("SUCESSO: .env carregado! URL: https://localhost:" + port);
    }
    dotenv.entries().forEach(entry -> {
        System.setProperty(entry.getKey(), entry.getValue());
    });

    SpringApplication.run(App.class, args);
}
}