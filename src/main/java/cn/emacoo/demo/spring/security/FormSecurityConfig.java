package cn.emacoo.demo.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Emac
 * @since 2019-07-21
 */
@Configuration
@Order(1)
public class FormSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(new AntPathRequestMatcher("/form/**"));
        http.antMatcher("/form/**")
                .authorizeRequests()
                .antMatchers("/form/login").anonymous()
                .anyRequest().fullyAuthenticated()
                .and().formLogin().loginPage("/form/login").failureUrl("/form/login?error").defaultSuccessUrl("/form")
                .and().logout().logoutUrl("/form/logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password("$2a$10$ZC.G/U5NR0WIzFbJRLiIvuzH7.1586NhurJN3BWsIDVOezDHkUhji").roles("ADMIN");
    }
}
