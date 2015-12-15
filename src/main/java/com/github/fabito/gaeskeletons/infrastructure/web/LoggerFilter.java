package com.github.fabito.gaeskeletons.infrastructure.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;


public class LoggerFilter implements Filter {
	
	
    private static final Logger LOGGER = Logger.getLogger(LoggerFilter.class.getName());

	public void destroy() {
		// Nothing to do
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest((HttpServletRequest) request);
		String body = CharStreams.toString(wrappedRequest.getReader());
		LOGGER.info(wrappedRequest.getRequestURI());
		LOGGER.info(body);
		wrappedRequest.resetInputStream();
		chain.doFilter(wrappedRequest, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// Nothing to do
	}

	private static class ResettableStreamHttpServletRequest extends
			HttpServletRequestWrapper {

		private byte[] rawData;
		private HttpServletRequest request;
		private ResettableServletInputStream servletStream;

		public ResettableStreamHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.servletStream = new ResettableServletInputStream();
		}


		public void resetInputStream() {
			servletStream.stream = new ByteArrayInputStream(rawData);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (rawData == null) {
				rawData = ByteStreams.toByteArray(this.request.getInputStream());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return servletStream;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			if (rawData == null) {
				rawData = ByteStreams.toByteArray(this.request.getInputStream());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return new BufferedReader(new InputStreamReader(servletStream));
		}
		

		private class ResettableServletInputStream extends ServletInputStream {

			private InputStream stream;

			@Override
			public int read() throws IOException {
				return stream.read();
			}
		}
	}

	
}
