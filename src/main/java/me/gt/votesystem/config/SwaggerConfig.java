package me.gt.votesystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${server.url}")
    private String serverUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("Vote System API")
                .version("1.0.0 Beta")
                .description("線上投票系統 API");

        return new OpenAPI()
                .info(info)
                .servers(List.of(new Server().url(serverUrl)));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController( "/api", "/swagger-ui/index.html");
    }


}
