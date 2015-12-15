package com.github.fabito.gaeskeletons.api;

import java.util.Set;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;
import com.google.common.collect.ImmutableSet;

public class GuiceSSSModule extends GuiceSystemServiceServletModule {

    final Set<Class<?>> serviceClasses = 
    		ImmutableSet.<Class<?>> of(
            FooApiV1.class
     );

    @Override
    protected void configureServlets() {
        super.configureServlets();
        this.serveGuiceSystemServiceServlet("/_ah/spi/*", serviceClasses);
    }

}
