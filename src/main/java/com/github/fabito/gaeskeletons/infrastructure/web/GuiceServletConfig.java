package com.github.fabito.gaeskeletons.infrastructure.web;

import com.github.fabito.gaeskeletons.api.GuiceSSSModule;
import com.github.fabito.gaeskeletons.domain.model.FooRepository;
import com.github.fabito.gaeskeletons.domain.model.User;
import com.github.fabito.gaeskeletons.infrastructure.persistence.OfyFooRepository;
import com.github.fabito.gaeskeletons.infrastructure.persistence.Transact;
import com.github.fabito.gaeskeletons.infrastructure.persistence.TransactInterceptor;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;

import javax.inject.Named;


public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {
            	bind(FooRepository.class).to(OfyFooRepository.class).in(Scopes.SINGLETON);
                bind(LoggerFilter.class).in(Scopes.SINGLETON);
                bind(ObjectifyFilter.class).in(Scopes.SINGLETON);
                bind(JwtSecurityFilter.class).in(Scopes.SINGLETON);
                bind(TenantFilter.class).in(Scopes.SINGLETON);

            	bindInterceptor(Matchers.annotatedWith(Api.class), Matchers.annotatedWith(ApiMethod.class), new TracingInterceptor());
            	bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transact.class), new TransactInterceptor());

                filter("/*").through(ObjectifyFilter.class);
            	filter("/_ah/spi/com.github.fabito.*").through(LoggerFilter.class);
                filter("/_ah/spi/com.github.fabito.*").through(JwtSecurityFilter.class);
            }

            @Provides
            @Named("user")
            @RequestScoped
            User provideUser() {
                throw new IllegalStateException("user id must be manually seeded");
            }


        }, new GuiceSSSModule());
    }
}
