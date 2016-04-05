package com.github.fabito.gaeskeletons.infrastructure.web;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class JwtSecurityFilterTest {

    @Test
    public void rsaJwkGenerator() throws JoseException {
        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        rsaJsonWebKey.setKeyId("123");
        System.out.print(rsaJsonWebKey.toJson());
    }


}
