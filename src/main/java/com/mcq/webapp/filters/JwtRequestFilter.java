
package com.mcq.webapp.filters;
//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE1ODg4MDA5NzIsImlhdCI6MTU4ODc4Mjk3Mn0.HjxFudctBCszK8Rq1NkQls8gmVHFcz-UyT5Qjl5dSMLIw7jeLJhKnBg43vOaz6eO4ufcnpQjUNenbKPGiVRJAQ

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import com.mcq.webapp.config.JwtUtilConfig;
import com.mcq.webapp.controller.UserController;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

    // @Autowired
    // private MainUserDetailService mainUserDetails;
    @Autowired
    private UserController mainUserDetails;


    @Autowired
    private JwtUtilConfig jwtutil;

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
     final String autherizationHeader=request.getHeader("Authorization");

     String username=null;
     String jwt=null;

     if(autherizationHeader!=null && autherizationHeader.startsWith("Bearer ")){
         jwt=autherizationHeader.substring(7);
         username=jwtutil.getUsernameFromToken(jwt);

     }

     if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
         UserDetails userDetails=this.mainUserDetails.loadUserByUsername(username);
         if(jwtutil.validateToken(jwt, userDetails)){
             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
         }
     }
     filterChain.doFilter(request, response);
}
}