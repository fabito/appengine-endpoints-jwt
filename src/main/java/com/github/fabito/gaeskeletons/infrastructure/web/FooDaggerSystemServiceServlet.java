package com.github.fabito.gaeskeletons.infrastructure.web;

import com.github.fabito.gaeskeletons.api.FooApp;

public class FooDaggerSystemServiceServlet extends DaggerSystemServiceServlet<FooApp> {

    public FooDaggerSystemServiceServlet() {
        super(FooApp.class);
    }

}
