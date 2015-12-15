package com.github.fabito.gaeskeletons.infrastructure.persistence;

import com.github.fabito.gaeskeletons.domain.model.Foo;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;

public class OfyService {

	static {
		JodaTimeTranslators.add(factory());
        register(Foo.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    private static void register(Class<?> clazz) {
        factory().register(clazz);
    }

    private static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
    
}