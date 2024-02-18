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
    public UserDetailsManager userDetails(DataSource dataSource) {
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(configure -> configure
                        .requestMatchers("/employees").hasAnyRole(Roles.ADMIN.name(), String.valueOf(Roles.MANAGER), String.valueOf(Roles.EMPLOYEE))
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/employees/list")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configure -> configure.accessDeniedPage("/access-denied"));
        return httpSecurity.build();
    }
}


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests(configure -> configure
////                        .requestMatchers("/").hasRole(String.valueOf(Roles.EMPLOYEE))
////                        .requestMatchers("/leaders/**").hasRole(String.valueOf(Roles.MANAGER))
////                        .requestMatchers("/systems/**").hasRole(String.valueOf(Roles.ADMIN)) // ** means all sub-directories
//                        .requestMatchers("/").hasAnyRole(String.valueOf(Roles.EMPLOYEE), String.valueOf(Roles.MANAGER), String.valueOf(Roles.ADMIN))
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form ->
//                        form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/authenticateTheUser")
//                                .defaultSuccessUrl("/list")
//                                .permitAll()
//                )
//                .logout(logout -> logout.permitAll())
//                .exceptionHandling(configure ->
//                        configure.accessDeniedPage("/access-denied")
//                );
//        // "loginPage" is a mapping to the login controller built by the user ( me ;-) )
//        // here authenticateTheUser is a Controller(No Controller mapping required) provided by Spring by default and no need to write code to authenticate the user;
//        return httpSecurity.build();
//    }
//}