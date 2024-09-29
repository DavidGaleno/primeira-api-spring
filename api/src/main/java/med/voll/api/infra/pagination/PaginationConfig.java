package med.voll.api.infra.pagination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class PaginationConfig {

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer() {
        return resolver -> {
            resolver.setPageParameterName("pagina");
            resolver.setSizeParameterName("tamanho");
        };
    }
}