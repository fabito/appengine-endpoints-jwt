package com.github.fabito.gaeskeletons.domain.model;

import com.googlecode.objectify.Key;

public interface FooRepository {

	Foo get(String key);
	
	Key<Foo> put(Foo foo);
	
}
