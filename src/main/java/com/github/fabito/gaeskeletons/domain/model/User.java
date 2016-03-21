package com.github.fabito.gaeskeletons.domain.model;

import com.google.common.base.MoreObjects;

public class User {

    String id;
    String email;
    String name;
    String avatarUrl;

    User() {
        //DI
    }

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("email", email)
                .add("name", name)
                .add("avatarUrl", avatarUrl)
                .toString();
    }
}
