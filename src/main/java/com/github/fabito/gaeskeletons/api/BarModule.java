package com.github.fabito.gaeskeletons.api;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;

@Module
public class BarModule {

    @Provides(type = Provides.Type.MAP)
    @ClassKey(BarApiV1.class)
    static BarApiV1 providesBarApiV1() {
        return new BarApiV1();
    }


}
