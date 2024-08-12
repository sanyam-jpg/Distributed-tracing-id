package org.traceid.forjava.interceptor.restTemplate;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.traceid.forjava.logger.LoggerFilter.TRACE_ID;

@Component
public class TraceHttpRequestInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request,byte[] body, ClientHttpRequestExecution execution) throws IOException {
    String traceId = MDC.get(TRACE_ID);
    if (traceId != null) {
      request.getHeaders().add(TRACE_ID, traceId);
    }
    return execution.execute(request, body);
  }
}
