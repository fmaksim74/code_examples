package ru.uralprom.komplat.rest;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author FedorchenkoMI
 *
 */
@SpringBootApplication
public class KomplatRestRepositoryService {

  @Value("${server.http.port}")
  private int httpPort;

  @Value("${server.http.enable}")
  private boolean enableHttp;

  public static void main(String[] args) {
    SpringApplication.run(KomplatRestRepositoryService.class, args);
  }

  @Bean
  public ServletWebServerFactory servletContainer() {
    
    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
    if (enableHttp) 
       tomcat.addAdditionalTomcatConnectors(createStandardConnector());
    return tomcat;
  }

  private Connector createStandardConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setPort(httpPort);
    return connector;
  }
}
