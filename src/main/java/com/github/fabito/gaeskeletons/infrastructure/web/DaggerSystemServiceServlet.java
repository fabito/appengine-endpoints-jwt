package com.github.fabito.gaeskeletons.infrastructure.web;

import com.google.api.server.spi.SystemServiceServlet;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DaggerSystemServiceServlet<C> extends SystemServiceServlet {

    private static final Logger logger = Logger.getLogger(DaggerSystemServiceServlet.class.getName());

    private Class<C> clazz;
    private Map<Class, Method> cache;

    public DaggerSystemServiceServlet(Class<C> clazz) {
        super();
        this.clazz = clazz;
        this.cache = buildCache();
    }

    @Override
    protected <T> T createService(Class<T> serviceClass) {
        final T service = super.createService(serviceClass);
        try {
            inject(app(), service);
        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.log(Level.SEVERE, "Error resolving dependencies", e);
            throw new RuntimeException(String.format("Cannot invoke method inject for type %s", serviceClass.getName()));
        }
        return service;
    }

    protected C app() {
        return clazz.cast(this.getServletContext().getAttribute("FooApp"));
    }

    protected Map<Class, Method> buildCache() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<Class, Method> cache = Maps.newHashMap();
        for (Method m : clazz.getDeclaredMethods()) {
            Class[] types = m.getParameterTypes();
            if ("inject".equals(m.getName()) && types.length == 1) {
                cache.put(types[0], m);
            }
        }
        logger.log(Level.FINE, "Time to build injector cache: {0}", stopwatch);
        return cache;
    }

    // Use for injecting targets of any type.
    private void inject(C component, Object target) throws InvocationTargetException, IllegalAccessException {
        Method m = cache.get(target.getClass());
        if (m != null) {
            m.invoke(component, target);
        }
    }
}
