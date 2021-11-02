package com.kryhowsky.shop.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            var claims = Jwts.parser()
                    .setSigningKey("akdsbgkasdbixzvzov345hkjefbgmvx")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();


            var email = claims.getSubject();
            var roles = claims.get("authorities", String.class);

            var grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
            if (roles != null && !roles.isEmpty()) {
                grantedAuthorities = Arrays.stream(roles.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toCollection(ArrayList::new));
            }

            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        } catch (ExpiredJwtException exception) {
            response.setStatus(401);
            log.warn("Token expired");
        }

    }
}
