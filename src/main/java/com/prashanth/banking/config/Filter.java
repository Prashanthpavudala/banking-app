package com.prashanth.banking.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class Filter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uuid = UUID.randomUUID().toString();
        try {
            // Put the UUID in the MDC
            MDC.put("uuid", uuid);
            log.info("Inside filter!!!!!!!!!!!");
            filterChain.doFilter(request, response);
        } finally {
            // Clear the MDC after processing the request
            MDC.remove("uuid");
        }
    }
}
