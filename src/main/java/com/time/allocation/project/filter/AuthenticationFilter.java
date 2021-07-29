package com.time.allocation.project.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.time.allocation.project.service.UserService;
import com.time.allocation.project.utility.AuthenticationUtility;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationUtility authenticationUtility;
    private UserService userService;
    private static final String AUTHORIZATION_HEADER =  "Authorization";
    private static final String BEARER_TOKEN_KEY = "Bearer ";

    public AuthenticationFilter(AuthenticationUtility authenticationUtility, UserService userService)
    {
    	this.authenticationUtility = authenticationUtility;
    	this.userService = userService;
    }
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        String token = null;
        String login = null;

        if(authorization != null  && authorization.startsWith(BEARER_TOKEN_KEY)) {
            token = authorization.substring(BEARER_TOKEN_KEY.length());
            login = authenticationUtility.getUsernameFromToken(token);
        }

        if(login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(login);

            if(authenticationUtility.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
