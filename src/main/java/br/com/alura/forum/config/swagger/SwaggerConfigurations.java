package br.com.alura.forum.config.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@SecurityRequirement(name = "bearer-key")
public class SwaggerConfigurations {
//
//	@Bean
//	public Docket forumApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//		        .select()
//		        .apis(RequestHandlerSelectors.basePackage("br.com.alura.forum"))
//		        .paths(PathSelectors.ant("/**"))
//		        .build()
//		        .ignoredParameterTypes(Usuario.class);
//	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("forumApi")
				.pathsToMatch("/**")
				
				.build();
	}

//	@Bean
//	public OpenAPI forumAluraOpenAPI() {
//		return new OpenAPI()
//				.info(new Info().title("Forum API").description("Projeto de Documentacao de API da Alura")
//						.version("v0.0.1").license(new License().name("Apache 2.0").url("http://springdoc.org")))
//				.components(new Components().addSecuritySchemes("bearer-key",
//						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
//				.externalDocs(new ExternalDocumentation().description("SpringShop Wiki Documentation")
//						.url("https://springshop.wiki.github.org/docs"));
//	}
	
//	@Bean
//	 public OpenAPI customOpenAPI() {
//	   return new OpenAPI()			   
//	          .components(new Components()
//	          .addSecuritySchemes("bearer-key",
//	          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").in(SecurityScheme.In.HEADER).bearerFormat("JWT")));
//	}


}
