package com.example.transport_backend.security;

import io.jsonwebtoken.*; // create / read / encription the token
import io.jsonwebtoken.security.Keys; /// used to create security key
import java.security.Key; /// it represent the security key object
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
@Component
public class JwtUtil {
	
	/// step 1 declare the secret string
	private final String SECRET="mysupersecretkeymysupersecretkey12345";
	
	Key key=Keys.hmacShaKeyFor(SECRET.getBytes());
	
	
	public String generateToken(String username, String role ,Integer id) {
		return Jwts.builder().setSubject(username).claim("role", role).claim("id", id).setIssuedAt( new Date()).setExpiration( new Date(System.currentTimeMillis()+86400000))
				.signWith(key,SignatureAlgorithm.HS256).compact();
	}
	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	public String extractRole(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role",String.class);
	}
	
	public Integer extractId(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("id",Integer.class);
	}
	
	// bellow to methods is need to call in jwt filter but already paresejwtclaims check all verficitation so bellow method is just useless
	
	
	public boolean validateToken(
	        String token,
	        UserDetails userDetails) {

	    String username =
	            extractUsername(token);

	    return username.equals(
	            userDetails.getUsername())
	            &&
	            !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(
	        String token) {

	    Date expiration =
	            Jwts.parserBuilder()
	                    .setSigningKey(key)
	                    .build()
	                    .parseClaimsJws(token)
	                    .getBody()
	                    .getExpiration();

	    return expiration.before(
	            new Date());
	}
	
}
