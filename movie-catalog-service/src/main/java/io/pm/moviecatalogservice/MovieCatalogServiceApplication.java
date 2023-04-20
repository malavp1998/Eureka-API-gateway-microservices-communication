package io.pm.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}


	@Bean
	public HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory()
	{
		// If service took more than 3ms than its will stop calling that service and will continue as usual
		int timeout = 5000;
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
				new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout);
		return clientHttpRequestFactory;
	}


	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate(getHttpComponentsClientHttpRequestFactory());
	}
}

