package org.traceid.forjava.config.restTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.traceid.forjava.interceptor.restTemplate.TraceHttpRequestInterceptor;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {

  @Autowired
  private TraceHttpRequestInterceptor interceptor;

  //bean for default restTemplate
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
        .interceptors(Collections.singletonList(interceptor))
        .build();
  }

  //if we want a specific rest Template bean for custom configration
  @Bean("A-Service")
  public RestTemplate ArestTemplate(RestTemplateBuilder builder) {
    int connectTimeout = 1000;
    int readTimeout = 1000;

    return builder
        .interceptors(Collections.singletonList(interceptor))
        .setConnectTimeout(Duration.ofMillis(connectTimeout))
        .setReadTimeout(Duration.ofMillis(readTimeout))
        .build();
  }
}
