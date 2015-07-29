package org.vferrer.stokker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    
}
