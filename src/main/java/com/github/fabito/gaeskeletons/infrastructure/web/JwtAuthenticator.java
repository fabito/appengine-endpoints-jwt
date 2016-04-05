package com.github.fabito.gaeskeletons.infrastructure.web;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Authenticator;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class JwtAuthenticator implements Authenticator {

    private static final Logger LOGGER = Logger.getLogger(JwtAuthenticator.class.getName());

    public User authenticate(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (!Strings.isNullOrEmpty(authorization)) {
            String jwt = authorization.replaceFirst("Bearer ", "");
            return new CustomUser("123", "fuechi@ciandt.com");
        }
        return null;
    }

    public static class CustomUser extends User {
        public CustomUser(String id, String email) {
            super(id, email);
        }
    }

}




