package com.fiap.postechgestaoprodutos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostechGestaoProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostechGestaoProdutosApplication.class, args);
	}

}
