package org.traceid.forjava.interceptor.apaceHttpClient;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import static org.traceid.forjava.logger.LoggerFilter.TRACE_ID;

@Component
public class ApacheClientHttpRequestInterceptor implements HttpRequestInterceptor {

  @Override
  public void process(HttpRequest request, HttpContext context) {
    String traceId = MDC.get(TRACE_ID);

    if (traceId != null) request.addHeader(TRACE_ID, traceId);

  }
}
