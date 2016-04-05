package com.github.fabito.gaeskeletons.infrastructure.web;

import com.github.fabito.gaeskeletons.api.DaggerFooApp;
import com.github.fabito.gaeskeletons.api.FooApp;

public class FooDaggerServletContextListener extends DaggerServletContextListener<FooApp> {

    @Override
    FooApp createApp() {
        return DaggerFooApp.create();
    }
}
