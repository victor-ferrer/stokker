package org.vferrer.stokker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:/META-INF/feederFlow.xml","classpath:/META-INF/historicalQuotations.xml","classpath:/META-INF/liveQuotations.xml"})
@EnableEurekaClient
// https://jira.spring.io/browse/DATAES-137
// This bug prevents Spring Data REST to work with ElasticSearchRepositories
// Fix Version is 1.3.0 GA (Gosling)
//@EnableElasticsearchRepositories(repositoryFactoryBeanClass = RestElasticsearchRepositoryFactoryBean.class)
public class StokkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StokkerApplication.class, args);
    }
    

}
