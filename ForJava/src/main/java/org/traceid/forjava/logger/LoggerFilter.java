package org.traceid.forjava.logger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class LoggerFilter extends OncePerRequestFilter {

  public static final String TRACE_ID = "trace-id";
  public static final String REQUEST_ID = "request-id";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    addTracingtIds(request);
    try {
      filterChain.doFilter(request, response);
    } finally {
      removeTracingIds();
    }
  }

  private void addTracingtIds(HttpServletRequest request) {

    String requestID = UUID.randomUUID().toString();

    String traceID = checkHeaderPresence(request,TRACE_ID) ? request.getHeader(TRACE_ID)
        : UUID.randomUUID().toString();

    MDC.put(TRACE_ID, traceID);
    MDC.put(REQUEST_ID, requestID);
  }

  private void removeTracingIds() {
    MDC.remove(REQUEST_ID);
    MDC.remove(TRACE_ID);
  }

  private boolean checkHeaderPresence(HttpServletRequest request, String headerName){
    return StringUtils.hasLength(request.getHeader(headerName));
  }

}


