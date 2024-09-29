package med.voll.api.infra.security.filters;

import jakarta.servlet.*;

import java.io.IOException;
import java.time.LocalDateTime;

//This is how we create Servlet Filters
//They are executed before Spring in the Filters layer
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            System.out.println("Requisição recebida em: " + LocalDateTime.now());
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
