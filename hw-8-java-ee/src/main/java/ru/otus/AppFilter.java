package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(value = "/*")
public class AppFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(AppFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String operation = httpServletRequest.getServletPath();
        double result;
        double first = Double.parseDouble(httpServletRequest.getParameter("first"));
        double second = Double.parseDouble(httpServletRequest.getParameter("second"));

        switch (operation) {
            case "/add":
                result = first + second;
                break;
            case "/subtract":
                result = first - second;
                break;
            case "/multiply":
                result = first * second;
                break;
            case "/div":
                if (second != 0) {
                    result = first / second;
                } else {
                    logger.error("Division by zero");
                    throw new ServletException("Division by zero is prohibited!");
                }
                break;
            default:
                logger.error("Invalid operation " + operation);
                throw new ServletException("Invalid operation");
        }

        logger.info("url: " + operation + ", result: " + result);
        chain.doFilter(request, response);
    }

}

