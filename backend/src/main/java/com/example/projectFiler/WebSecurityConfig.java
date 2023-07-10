package com.example.projectFiler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {

  //  @Override
  //  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
  //    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  //  }

  //  @Bean
  //  @Override
  //  public AuthenticationManager authenticationManagerBean() throws Exception {
  //    return super.authenticationManagerBean();
  //  }

  /* @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.cors().and().csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers("/api/auth/**").permitAll()
        .antMatchers("/api/test/**").permitAll().antMatchers("/**").permitAll()
        .anyRequest().authenticated();

      http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
*/

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      //.cors()
      //.disable()
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(
        auth -> auth.requestMatchers("/**").permitAll().anyRequest().authenticated()
      );

    return http.build();
  }
}
