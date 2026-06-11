package com.example.transport_backend.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.
UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.
SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.
SimpleGrantedAuthority;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.example.transport_backend.service.CustomUserDetailsService;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends GenericFilterBean{

	  @Autowired
	    private CustomUserDetailsService customUserDetailsService;

	    @Autowired
	    private JwtUtil jwtUtil;
	    
	    @Autowired
	    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    )
            throws IOException,
            ServletException {

        HttpServletRequest req=
                (HttpServletRequest)
                        request;

        String authHeader=
                req.getHeader(
                        "Authorization"
                );

        System.out.println("AUTH HEADER = " + authHeader);
        if(
                authHeader!=null
                &&
                authHeader.startsWith(
                        "Bearer "
                )
        ){

            String token=
                    authHeader.substring(
                            7
                    );

            System.out.println("TOKEN = " + token);
            
            
            try{


                String username =  jwtUtil.extractUsername(token);
  
                UserDetails userDetails= userDetailsService
                		.loadUserByUsername(
                		username
                		);

                UsernamePasswordAuthenticationToken
                authentication=

                new
                UsernamePasswordAuthenticationToken(

                userDetails,

                null,

                userDetails
                .getAuthorities()

                );


                SecurityContextHolder
                        .getContext()

                        .setAuthentication(
                                authentication
                        );

            }
            catch(Exception e){

                System.out.println(
                        "Invalid Token"
                );
                e.printStackTrace();

            }

        }

        chain.doFilter(
                request,
                response
        );

    }
}