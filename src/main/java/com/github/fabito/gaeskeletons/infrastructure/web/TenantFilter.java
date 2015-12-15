package com.github.fabito.gaeskeletons.infrastructure.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TenantFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(TenantFilter.class.getName());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOGGER.info("Before chain");
		HttpServletRequest req = (HttpServletRequest) request;
		LOGGER.info(req.getRequestURI());
		LOGGER.info(req.getLocalAddr());
		LOGGER.info(req.getRemoteAddr());
		LOGGER.info(req.getServerName());
		chain.doFilter(request, response);
		LOGGER.info("After chain");
	}

	@Override
	public void destroy() {
	}

}
