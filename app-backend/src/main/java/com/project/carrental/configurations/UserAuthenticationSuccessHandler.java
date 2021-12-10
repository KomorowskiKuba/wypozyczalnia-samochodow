package com.project.carrental.configurations;

import com.project.carrental.models.ApplicationUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
@Configuration
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException, RuntimeException
    {
        HttpSession session = httpServletRequest.getSession();
        ApplicationUser authUser = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        session.setAttribute("username", authUser.getUsername());
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        authorities.forEach(authority ->
        {
            if(authority.getAuthority().equals("ROLE_ADMIN"))
            {
                session.setAttribute("role", "ROLE_ADMIN");//, AppRole.ADMIN);
                try
                {
                    httpServletResponse.sendRedirect("/carsapi/cars");
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
            else if (authority.getAuthority().equals("ROLE_USER"))
            {
                session.setAttribute("role", "ROLE_USER");//, AppRole.ADMIN);
                try
                {
                    httpServletResponse.sendRedirect("/user/home");
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
