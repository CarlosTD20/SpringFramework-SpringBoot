package com.carlos.curso.springboot.app.springboot_crud.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class TokenJwtConfig {
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
