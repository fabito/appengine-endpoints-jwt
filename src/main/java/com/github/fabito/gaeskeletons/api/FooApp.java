package com.github.fabito.gaeskeletons.api;

import dagger.Component;

@Component(modules={FooModule.class, BarModule.class})
public interface FooApp {

    void inject(FooApiV1 fooApiV1);

    void inject(BarApiV1 barApiV1);

}