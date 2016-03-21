package com.github.fabito.gaeskeletons.api;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.fabito.gaeskeletons.domain.model.Foo;
import com.github.fabito.gaeskeletons.domain.model.FooRepository;
import com.github.fabito.gaeskeletons.infrastructure.web.JwtAuthenticator;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.inject.Provider;

@Api(name = "foobar", version = "v1", description = "Foo API", 
     clientIds = {com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID},
	 resource = "foos",
	 authenticators = {JwtAuthenticator.class}
)
@Singleton
public class FooApiV1 {

    private static final Logger LOGGER = Logger.getLogger(FooApiV1.class.getName());

	private Provider<com.github.fabito.gaeskeletons.domain.model.User> userProvider;
    private FooRepository repository;

	@Inject
	public FooApiV1(FooRepository repository, @javax.inject.Named("user") Provider<com.github.fabito.gaeskeletons.domain.model.User> userProvider) {
		super();
		this.repository = repository;
		this.userProvider = userProvider;
	}

    @ApiMethod(path = "{tenantId}/foos/{id}", httpMethod = "GET")
    public FooResponse get(@Named("tenantId") String tenantId,
    		@Named("id") String id) {

    	LOGGER.info(id);
		LOGGER.info(userProvider.get().toString());

    	return new FooResponse();
    }
    
    @ApiMethod(path = "{tenantId}/foos", httpMethod = "POST")
    public FooResponse post(@Named("tenantId") String tenantId, Foo foo) {
		LOGGER.info(userProvider.get().toString());
    	LOGGER.info(foo.toString());
    	repository.put(foo);
    	return new FooResponse();
    }
    
}