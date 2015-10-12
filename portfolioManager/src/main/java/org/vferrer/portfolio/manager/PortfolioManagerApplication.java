package org.vferrer.portfolio.manager;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PortfolioManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioManagerApplication.class, args);
    }
    
//	@Bean
//	public ServletRegistrationBean servletRegistrationBean(){
//	    return new ServletRegistrationBean(new  org.h2.server.web.WebServlet(),"/h2console/**");
//	}

    // TODO This is used for the makeshift login form authentication and should be replaced
    // by the OAuth2 tokens
    @RequestMapping("/user")
    public Principal user(Principal user) {
      return user;
    }
	
    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
      @Override
      protected void configure(HttpSecurity http) throws Exception {
        http
          .httpBasic()
        .and()
          .authorizeRequests()
            .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .logout();
      }
    }

    
}
