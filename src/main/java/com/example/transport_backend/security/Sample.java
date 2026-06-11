package com.example.transport_backend.security;
import java.util.Date;
import java.security.Key;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

public class Sample {

	private final String SECRET="karthi8825750992";
	
	Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public String createToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt( new Date()).setExpiration( new Date(System.currentTimeMillis()+8640000)).signWith(key).compact();
	}
	
	public String readuser(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody().getSubject();
	}
}
