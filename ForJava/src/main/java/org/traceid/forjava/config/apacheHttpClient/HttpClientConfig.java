package org.traceid.forjava.config.apacheHttpClient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.traceid.forjava.interceptor.apaceHttpClient.TraceHttpRequestInterceptor;

@Configuration
public class HttpClientConfig {

  @Autowired
  private TraceHttpRequestInterceptor interceptor;

  @Bean
  public CloseableHttpClient createHttpClient() {
    // Create custom request configuration if needed
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectTimeout(5000)
        .setSocketTimeout(5000)
        .build();

    // Build HttpClient with the interceptor
    return HttpClientBuilder.create()
        .setDefaultRequestConfig(requestConfig)
        .addInterceptorFirst(interceptor) // Add interceptor here
        .build();
  }
}
