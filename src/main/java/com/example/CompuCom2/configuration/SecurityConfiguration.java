package com.example.CompuCom2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bower_components/**","/css/**","/dist/*","/img/**","/js/**","/plugins/**","/vendor/**", "/files").permitAll()
                .antMatchers("/banner/show-deals", "/banner/save-deals", "/banner/change-status", "/banner/delete-banner").hasRole("ADMIN")
                .antMatchers("/banner/files", "/banner/image").permitAll()
                .antMatchers("/","/index","/about","/questions","/fproducts","/search","/more").permitAll()
                .antMatchers("/login","/registro").permitAll()
                .antMatchers("/category/**").hasRole("ADMIN")
                .antMatchers("/products/productform","/products/addproduct","/products/showproducts","/products/removeproduct").hasRole("ADMIN")
                .antMatchers("/products/files").permitAll()
                .antMatchers("/sales/**").hasRole("ADMIN")
                .antMatchers("/shippings/**").hasRole("ADMIN")
                .antMatchers("/shopping_cart/**").hasRole("USER")
                .antMatchers("/statistics/**").hasRole("ADMIN")
                .antMatchers("/users/userform", "/users/showusers", "/users/removeUser", "/users/update-user","/users/searchuser").hasRole("ADMIN")
                .antMatchers("/users/edit-user", "/users/update-password", "/users/save-user", "/users/history").hasRole("USER")
                .antMatchers("/users/adduser", "/users/add-user", "/users/adduser", "/users/adduser").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/loginsuccess").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/index?logout=true")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
