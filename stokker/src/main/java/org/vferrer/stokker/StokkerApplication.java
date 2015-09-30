package org.vferrer.stokker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@ImportResource(value = {"classpath:/META-INF/feederFlow.xml","classpath:/META-INF/historicalQuotations.xml","classpath:/META-INF/liveQuotations.xml"})
@EnableElasticsearchRepositories(basePackages = "org/vferrer/stokker/elk")
public class StokkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StokkerApplication.class, args);
    }
}
