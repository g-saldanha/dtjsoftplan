package com.gbldev.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Este Bean tem como objetivo configurar a documentação da API de Pessoas. A documentação gerada será do tipo swagger.
 */
@Configuration
@SecurityScheme(
		name = "basicAuth",
		type = SecuritySchemeType.HTTP,
		scheme = "basic"
)
public class SwaggerConfig {
	private final BuildProperties buildProperties;

	@Autowired
	public SwaggerConfig(final BuildProperties buildProperties) {
		this.buildProperties = buildProperties;
	}

	/**
	 * Método responsável por realizar o setup de configuração da documentação, mapear APIs,
	 * criação de tags e informações gerais da API.
	 *
	 * @return - Retorna a configuração definida
	 */
	@Bean
	public OpenAPI api() {
		return new OpenAPI()
				.info(this.apiInfo());
	}

	/**
	 * Cria as informações da API.
	 */
	private Info apiInfo() {
		return new Info()
				.title("Teste Softplan: API de Pessoas")
				.version(this.buildProperties.getVersion());
	}

}
