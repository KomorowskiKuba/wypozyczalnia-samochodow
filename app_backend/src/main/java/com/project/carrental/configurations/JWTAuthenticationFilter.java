package com.project.carrental.configurations;

import com.project.carrental.models.User;

import com.project.carrental.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private UserService userService;

    private JWTTokenHelper jwtTokenHelper;

    public JWTAuthenticationFilter(UserService userService, JWTTokenHelper jwtTokenHelper) {
        this.userService = userService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken = jwtTokenHelper.getAuthorizationHeader(request);

        if (authorizationToken != null) {
            String userName = jwtTokenHelper.getUsernameFromToken(authorizationToken);

            if (userName != null) {
                User user = userService.loadUserByUsername(userName);

                if (jwtTokenHelper.validateToken(authorizationToken, user)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
