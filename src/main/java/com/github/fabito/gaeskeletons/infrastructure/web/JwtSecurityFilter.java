package com.github.fabito.gaeskeletons.infrastructure.web;

import com.github.fabito.gaeskeletons.domain.model.User;

import com.google.api.server.spi.response.UnauthorizedException;
import com.google.common.base.Strings;
import com.google.inject.Key;
import com.google.inject.name.Names;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtSecurityFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorization = httpRequest.getHeader("Authorization");
        if (!Strings.isNullOrEmpty(authorization)) {
            String jwt = authorization.replaceFirst("Bearer ", "");
            User user = new User("123", "fabio@gmail.com");
            httpRequest.setAttribute(Key.get(User.class, Names.named("user")).toString(), user);
            chain.doFilter(request, response);
            return;
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(401);
            httpResponse.getWriter().write("Invalid or non existent bearer token");
        }
    }

    public void destroy() {}

    public void init(FilterConfig filterConfig) throws ServletException {}
}