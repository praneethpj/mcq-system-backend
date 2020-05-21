package com.mcq.webapp.config;

import java.util.Arrays;

import com.mcq.webapp.controller.UserController;
import com.mcq.webapp.filters.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
  // @Autowired
  //  private MainUserDetailService userDetailService;
   @Autowired
   private UserController userDetailService;

  @Autowired
  private JwtRequestFilter jwtreqestfilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailService);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
      .authorizeRequests().antMatchers("/authenticate").permitAll()
     .anyRequest().authenticated()
     .and().sessionManagement()
     .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     http.addFilterBefore(jwtreqestfilter,UsernamePasswordAuthenticationFilter.class);
     http.cors().and().csrf().disable();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
      return NoOpPasswordEncoder.getInstance();
    }

    @Override
public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/api/registration");
    web.ignoring().antMatchers("/api/notes");
    web.ignoring().antMatchers("/api//teacher");
   // web.ignoring().antMatchers("/api/teacher/{id}");
    web.ignoring().antMatchers("/api/student");
    web.ignoring().antMatchers("/api/grade");
    web.ignoring().antMatchers("/api/level");
    web.ignoring().antMatchers("/api/term");
    web.ignoring().antMatchers("/api/subject");
    web.ignoring().antMatchers("/api/lesson");
    web.ignoring().antMatchers("/api/admin");
    web.ignoring().antMatchers("/api/teachersactivities");
    web.ignoring().antMatchers("/api/studentsactivities");

    web.ignoring().antMatchers("/api/payment");
    web.ignoring().antMatchers("/api/questions");
    web.ignoring().antMatchers("/api/answers");
    web.ignoring().antMatchers("/api/questionsformat");
    web.ignoring().antMatchers("/api/questionsformatlevel");

}

}