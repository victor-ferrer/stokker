package org.vferrer.stokker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/META-INF/feederFlow.xml")
public class StokkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StokkerApplication.class, args);
    }
}
