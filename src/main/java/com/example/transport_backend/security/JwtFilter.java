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
                /// Now actually the UserDetails Service is executed (CustomUserDetailService) 
                		/// is executed so in that method it check the user details is actually present or not
                		/// if it present it return the userDetails object other wise it return user not found 

                
                UsernamePasswordAuthenticationToken
                authentication=

                new
                UsernamePasswordAuthenticationToken(

                userDetails, // this is the principle of authentication //auth.getPrincipal() like later so we can access by creating the @AuthendicationPrinciple

                null,

                userDetails
                .getAuthorities()

                ); /// its act like a card it hold the userDetails like userName, password and authorities 


                SecurityContextHolder
                        .getContext()

                        .setAuthentication(
                                authentication
                        ); /// now the spring know who is the current request holder like we store current user into this spring  and store this object in spring 

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
        ); // passing the request to next filters and controller

    }
}