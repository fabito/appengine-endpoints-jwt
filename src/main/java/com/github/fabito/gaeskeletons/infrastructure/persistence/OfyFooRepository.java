package com.github.fabito.gaeskeletons.infrastructure.persistence;

import com.github.fabito.gaeskeletons.domain.model.Foo;
import com.github.fabito.gaeskeletons.domain.model.FooRepository;
import com.googlecode.objectify.Key;

public class OfyFooRepository implements FooRepository {

	public Foo get(String key) {
		return OfyService.ofy().load().key(Key.<Foo>create(Foo.class,key)).now();
	}

	@Override
	public Key<Foo> put(Foo foo) {
		return OfyService.ofy().save().entity(foo).now();
	}

}
