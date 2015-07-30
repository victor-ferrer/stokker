package org.vferrer.stokker;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
@ImportResource("classpath:/META-INF/feederFlow.xml")
public class StokkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StokkerApplication.class, args);
    }
    
    @RequestMapping("/")
    public String home() {
    	return "Hello World";
    }

    
    /**
     * Instantiates and registers the servlet required for running the H2 database console
     * @return
     */
    @Bean
    public ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/h2console/*");
        return registrationBean;
    }
    
}
