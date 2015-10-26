package org.vferrer.portfolio.manager;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

@SpringBootApplication
@RestController
@RequestMapping("/portfoliomanager")
public class PortfolioManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioManagerApplication.class, args);
    }
    
//	@Bean
//	public ServletRegistrationBean servletRegistrationBean(){
//	    return new ServletRegistrationBean(new  org.h2.server.web.WebServlet(),"/h2console/**");
//	}

    @RequestMapping("/user")
    public Principal user(Principal user) {
      return user;
    }
	
	@Controller
	public static class LoginErrors {

		@RequestMapping("/portfoliomanager/login")
		public String dashboard() {
			return "redirect:/#/";
		}

	}
    
	@Component
	@EnableOAuth2Sso
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter     {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/portfoliomanager/**").authorizeRequests()
					.antMatchers(actuatorEndpoints()).hasRole("ADMIN")
					.anyRequest().authenticated().and().csrf()
					.csrfTokenRepository(csrfTokenRepository()).and()
					.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
					.logout().logoutUrl("/portfoliomanager/logout").permitAll()
					.logoutSuccessUrl("/");
		}
		private Filter csrfHeaderFilter() {
			return new OncePerRequestFilter() {
				@Override
				protected void doFilterInternal(HttpServletRequest request,
						HttpServletResponse response, FilterChain filterChain)
						throws ServletException, IOException {
					CsrfToken csrf = (CsrfToken) request
							.getAttribute(CsrfToken.class.getName());
					if (csrf != null) {
						Cookie cookie = new Cookie("XSRF-TOKEN",
								csrf.getToken());
						cookie.setPath("/");
						response.addCookie(cookie);
					}
					filterChain.doFilter(request, response);
				}
			};
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}
		
	    private String[] actuatorEndpoints() {
	        return new String[]{"mappings"};
	    }
    	
    }

    
}
