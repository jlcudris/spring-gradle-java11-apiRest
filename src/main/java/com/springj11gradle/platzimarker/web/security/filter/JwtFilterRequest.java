package com.springj11gradle.platzimarker.web.security.filter;

import com.springj11gradle.platzimarker.domain.service.UserServiceDetails;
import com.springj11gradle.platzimarker.web.security.JWUTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {
    @Autowired
    private JWUTutil jwuTutil;
    @Autowired
    private UserServiceDetails userServiceDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader =request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt =authorizationHeader.substring(7);
            String username =jwuTutil.extracUsername(jwt);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null){
                UserDetails userDetails = userServiceDetails.loadUserByUsername(username);

                if(jwuTutil.validatetoken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

            }

        }
        filterChain.doFilter(request,response);
    }
}
