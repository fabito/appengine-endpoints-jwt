package com.github.fabito.gaeskeletons.infrastructure.web;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Authenticator;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;

public class JwtAuthenticator implements Authenticator {

    public User authenticate(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        if (!Strings.isNullOrEmpty(authorization)) {
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




