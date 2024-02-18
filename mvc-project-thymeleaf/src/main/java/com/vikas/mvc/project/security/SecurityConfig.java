package com.vikas.mvc.project.security;

import com.vikas.mvc.project.entity.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // For custom tables in database
    @Bean
    public UserDetailsManager userDetails(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // retrieve a user by user_id
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, active from member where username=?"
        );
        // retrieve a user's role/authorities by user_id
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, user_role from role where username=?"
        );

        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configure -> configure
                        .requestMatchers("/").hasRole(Roles.EMPLOYEE.name())
                        .requestMatchers("/leaders**").hasRole(Roles.MANAGER.name())
                        .requestMatchers("/systems/**").hasRole(Roles.ADMIN.name()) // ** means all sub-directories
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/employees/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configure ->
                        configure.accessDeniedPage("/employees/access-denied")
                );
        // "loginPage" is a mapping to the login controller built by the user ( me ;-) )
        // here authenticateTheUser is a Controller(No Controller mapping required) provided by Spring by default and no need to write code to authenticate the user;
        return httpSecurity.build();
    }
}


  /* // Add support for JDBC... no more hard coding;
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
     */
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails vikas = User.builder()
//                .username("vikas")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//        UserDetails abdul = User.builder()
//                .username("abdul")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//        UserDetails prakash = User.builder()
//                .username("prakash")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(vikas, abdul, prakash);
//    }
