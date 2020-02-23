package com.antz.financial.infra.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author huang.huang
 * @description
 * @date 2020/2/23 16:52
 **/
@EnableSwagger2
@Configuration
public class SwaggerConfiguration extends WebMvcConfigurationSupport {


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.antz.financial.adapter.endpoint"))
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        String title = "Financial";
        String description = "Financial";
        String version = "v.0.0.1";
        String termsOfServiceUrl = "http://127.0.0.1:8080";
        return new ApiInfo(
                title, description, version, termsOfServiceUrl,
                new Contact("Antz-H", "", "huang7230468@163.com"),
                null, null, Lists.newArrayList()
        );
    }

}
