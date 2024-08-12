package org.traceid.forjava.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@Slf4j
public class Controller {

  @Autowired
    private RestTemplate restTemplate;

  @Autowired
  private HttpClient httpClient;


    @GetMapping("/r")
    public ResponseEntity<Void> testRestTemplate() {
      //we want to send request to another server using restTemplate
      //instead of creating another service, we can use webhooks or requestBin. Go to any of these and paste your url below
      String url = "https://eoqweqpgdoct8cb.m.pipedream.net";
      log.info("request sent using rest Template");
      restTemplate.exchange(url, HttpMethod.GET, null, String.class);
      return ResponseEntity.ok().build();
    }

  //send request using apache http client
  @GetMapping("/a")
  public ResponseEntity<Void> testApacheHttpClient() throws IOException {
    String url = "https://eoqweqpgdoct8cb.m.pipedream.net";

    HttpGet request = new HttpGet(url);
    log.info("request sent using apache Http Client Template");
    httpClient.execute(request);
    return ResponseEntity.ok().build();

  }
}
