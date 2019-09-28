package cn.emacoo.demo.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Emac
 * @since 2019-07-21
 */
@Configuration
@Order(2)
public class HttpBasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(new AntPathRequestMatcher("/basic/**"));
        http.antMatcher("/basic/**")
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic();
    }
}
