package com.github.fabito.gaeskeletons.api;

import com.github.fabito.gaeskeletons.infrastructure.web.JwtAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name = "bar",
     version = "v1",
     description = "Bar API",
     clientIds = {com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID},
     authenticators = {JwtAuthenticator.class}
)
public class BarApiV1 {

    @ApiMethod(path = "/bars/{id}", httpMethod = "GET")
    public JwtAuthenticator.CustomUser get(User user, @Named("id") String id) {

        JwtAuthenticator.CustomUser u = (JwtAuthenticator.CustomUser) user;



        return u;
    }

}
