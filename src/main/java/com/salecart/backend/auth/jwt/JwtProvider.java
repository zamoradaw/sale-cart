package com.salecart.backend.auth.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.salecart.backend.model.entity.MainUser;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expirate}")
	private int expirate;
	
	private final static Logger log = LoggerFactory.getLogger(JwtProvider.class);
	
	public String generateToken(Authentication authentication) {
		MainUser mainUser = (MainUser) authentication.getPrincipal(); 
		String username = mainUser.getUsername();
		List<String> rols = mainUser.getAuthorities().stream()
													 .map(GrantedAuthority::getAuthority)
													 .collect(Collectors.toList());
		Date dateCreated = new Date();
		Date timeExpirate = new Date(new Date().getTime() + (expirate * 1000));
		SignatureAlgorithm algorithmType = SignatureAlgorithm.HS512;
		
		return Jwts.builder().setSubject(username)
							 .claim("rol", rols)
							 .setIssuedAt(dateCreated)
							 .setExpiration(timeExpirate)
							 .signWith(algorithmType, secret.getBytes())
							 .compact();
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes())
				            .parseClaimsJws(token)
				            .getBody()
				            .getSubject();
	}
	
	public boolean validationToken(String token) {
		try {
	
			Jwts.parser().setSigningKey(secret.getBytes())
						 .parseClaimsJws(token);
			
			return true;
		}catch(MalformedJwtException e) {
			log.error("Mal formato del token");
		}catch(UnsupportedJwtException e) {
			log.error("Token no soportado");
		}catch(ExpiredJwtException e) {
			log.error("Token expirado");
		}catch(IllegalArgumentException e) {
			log.error("Token vacío");
		}catch(SignatureException e) {
			log.error("Firma inválida");
		}
		
		return false;
	}
}
