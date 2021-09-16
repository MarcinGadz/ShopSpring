package com.mg.demo.util;

import com.mg.demo.entity.User;
import com.mg.demo.service.UserDetailsService;
import com.mg.demo.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class TokenProvider {

    private UserDetailsService service;

    @Autowired
    public void setService(UserDetailsService service) {
        this.service = service;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);
    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expirationDateTime = now.plus(600000, ChronoUnit.MILLIS);

        Date issueDate = Date.from(now.toInstant());
        Date expirationDate = Date.from(expirationDateTime.toInstant());

        return Jwts.builder().setSubject(authentication.getName()).claim("auth", authorities)
                .signWith(SignatureAlgorithm.HS512, this.secretKey).setIssuedAt(issueDate).setExpiration(expirationDate).compact();
    }

    public Authentication getAuthentication(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();

//        Collection<? extends GrantedAuthority> authorities;
//        authorities = Collections.emptyList();
//        User principal = new User(claims.getSubject(), "", authorities);
        UserDetails principal = service.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.info("Invalid JWT signature: " + e.getMessage());
            LOGGER.debug("Exception " + e.getMessage(), e);
            return false;
        }
    }

}
