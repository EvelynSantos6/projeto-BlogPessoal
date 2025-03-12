package com.generation.blogpessoal.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

		//atributo que armazena a chave de assinatura - codificar SHA256
		public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

		private Key getSignKey() {
			byte[] keyBytes = Decoders.BASE64.decode(SECRET);
			return Keys.hmacShaKeyFor(keyBytes);
		}

		private Claims extractAllClaims(String token) {
			return Jwts.parserBuilder()
					.setSigningKey(getSignKey()).build()
					.parseClaimsJws(token).getBody();
		}
		//extrair informações do token - geralmente T de type
		public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);
		}

		public String extractUsername(String token) {
			return extractClaim(token, Claims::getSubject);
		}
		
		public Date extractExpiration(String token) {
			return extractClaim(token, Claims::getExpiration);
		}
		//validade do token (data)
		private Boolean isTokenExpired(String token) {
			return extractExpiration(token).before(new Date());
		}
		
		public Boolean validateToken(String token, UserDetails userDetails) {
			final String username = extractUsername(token);
			return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		}
		//cria o token
		private String createToken(Map<String, Object> claims, String userName) {
			return Jwts.builder()
						.setClaims(claims)
						.setSubject(userName)
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
						.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
		}
		//data atual + 1000 *60 *60 => 1hr
		public String generateToken(String userName) {
			Map<String, Object> claims = new HashMap<>();
			return createToken(claims, userName);
	}

}
