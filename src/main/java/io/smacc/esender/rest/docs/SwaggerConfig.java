package io.smacc.esender.rest.docs;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("io.smacc.esender.rest")
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error")))
				.build()
				.apiInfo(apiInfo());
	}


	private ApiInfo apiInfo() {
		String description = "Email sender";
		return new ApiInfoBuilder()
				.title("Email sender")
				.description(description)
				.termsOfServiceUrl("https://github.com/suhomud/email-sender")
				.license("smacc")
				.version("1.0")
				.contact(new Contact("Yevhen Sukhomud", "https://github.com/suhomud/email-sender", "suhomud@gmail.com"))
				.build();
	}

}
