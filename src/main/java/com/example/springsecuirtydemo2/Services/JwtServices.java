package com.example.springsecuirtydemo2.Services;

import java.security.Key;
import java.sql.Date;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServices {
	
	public final static String SECERET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	public static  String generateToken(String username) {
		HashMap<String,Object> claims = new HashMap<String, Object>();
		
		return createToken(username,claims);
	}
	
	
	public static  String createToken(String username,HashMap<String, Object> claims) {
		
		return Jwts.builder().setClaims(claims).setSubject(username).setExpiration(new Date(System.currentTimeMillis()+30*60*1000) ).setIssuedAt(new Date(System.currentTimeMillis())).signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
		
		 

	}


	private static Key getSignKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECERET);
		return Keys.hmacShaKeyFor(keyByte);
	}


	public static boolean validateToken(String token) {
		
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
		//if above is excuted then only it will go to below line and return true if not it will throw exception. Besides the above will return claims which conatins subject,payload all stuff
		return true;
	}
}
