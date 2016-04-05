package com.github.fabito.gaeskeletons.infrastructure.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public abstract class DaggerServletContextListener<T> implements ServletContextListener {

    private volatile T app;

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		final ServletContext servletContext = servletContextEvent.getServletContext();
        app = createApp();
		servletContext.setAttribute("FooApp", app);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		final ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.removeAttribute("FooApp");
	}

    abstract T createApp();

}
