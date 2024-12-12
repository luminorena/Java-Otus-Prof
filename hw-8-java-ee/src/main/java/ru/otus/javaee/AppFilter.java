package ru.otus.javaee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;


@WebFilter(value = "/*")
public class AppFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(AppFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        logger.info(new Date() + " - " + httpServletRequest.getServletPath());
        chain.doFilter(request, response);
    }



}

