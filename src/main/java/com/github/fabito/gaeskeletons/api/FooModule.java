package com.github.fabito.gaeskeletons.api;

import com.github.fabito.gaeskeletons.domain.model.FooRepository;
import com.github.fabito.gaeskeletons.infrastructure.persistence.OfyFooRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class FooModule {

    @Provides
    static FooRepository providesFooRepository() {
        return new OfyFooRepository();
    }

}
