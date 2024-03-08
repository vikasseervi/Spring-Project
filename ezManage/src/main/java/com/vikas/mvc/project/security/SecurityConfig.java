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
                        .requestMatchers("/employees").hasAnyRole(Roles.ROLE_ADMIN.name(), Roles.ROLE_MANAGER.name(), Roles.ROLE_EMPLOYEE.name())
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/employees/list")
                        .permitAll())
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login") // Redirect to login page after logout
                                .invalidateHttpSession(true) // Invalidate session
                                .deleteCookies("JSESSIONID") // Delete cookies
                )
                .exceptionHandling(configure -> configure.accessDeniedPage("/access-denied"));
        return httpSecurity.build();
    }
}
