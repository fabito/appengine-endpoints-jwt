package com.github.fabito.gaeskeletons.infrastructure.web;

import java.util.Arrays;
import java.util.logging.Logger;

import com.google.common.base.Stopwatch;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TracingInterceptor implements MethodInterceptor {

	private static final Logger LOGGER = Logger
			.getLogger(TracingInterceptor.class.getName());

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Stopwatch stopwatch = Stopwatch.createStarted();
		try {
			return invocation.proceed();
		} finally {
			LOGGER.info(String
					.format("Invocation of method %s.%s() with parameters %s took %s.",
							invocation.getClass().getName(),
							invocation.getMethod().getName(),
							Arrays.toString(invocation.getArguments()),
							stopwatch.stop()));
		}
	}

}
