package com.dimevision.orkis.webapp.security;

import com.dimevision.orkis.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private EmployeeService employeeService;
    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource, EmployeeService employeeService) {
        this.dataSource = dataSource;
        this.employeeService = employeeService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("Admin")
                .and()
                .formLogin()    // TODO: 10/13/2021 Add form root
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll();
//                .antMatchers().hasAnyRole()

    }

//    /* IN-MEMORY AUTHENTICATION */
//    @Bean
//    public UserDetailsService employee() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$/2/9EJ3cSGwxCoPRgDmNYeItKJX0Cw.QsoN3RisJE4dNWWmLKCq62")
//                .roles("Client")
//                .build(); // Минимальная информация о юзерах
//
//        UserDetails admin = User.builder()
//                .username("Dimevision")
//                .password("{bcrypt}$2a$12$okjadBtxEnj2iiIriat8eOpNPWt8ySVWSZH9fgrAVI7soJuw2N7ry")
//                .roles("Admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider authenticationProvider =  new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(employeeService);

        return authenticationProvider;
    }
}
