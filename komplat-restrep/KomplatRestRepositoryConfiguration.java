/**
 * 
 */
package ru.uralprom.komplat.rest;

import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Metamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.uralprom.komplat.rest.jpa.turn.TurnEntityPKConverter;

/**
 * @author FedorchenkoMI
 */
@Configuration
public class KomplatRestRepositoryConfiguration implements RepositoryRestConfigurer {

  @Autowired
  private final TurnEntityPKConverter turnPKConverter = null;

  @Override
  public void configureConversionService(ConfigurableConversionService conversionService) {
    conversionService.addConverter(turnPKConverter);
    RepositoryRestConfigurer.super.configureConversionService(conversionService);
  }

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    
    ExposureConfiguration ec = config.getExposureConfiguration();

    ec.disablePatchOnItemResources();
    ec.disablePutForCreation();
    ec.disablePutOnItemResources();

    Metamodel mm = entityManager.getMetamodel();
    mm.getEntities().forEach(entityType -> {
      config.exposeIdsFor(entityType.getJavaType());
      });
    mm.getEmbeddables().forEach(entityType -> config.exposeIdsFor(entityType.getJavaType()));

    RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
  }

  @Override
  public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
    objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));

    RepositoryRestConfigurer.super.configureJacksonObjectMapper(objectMapper);
  }


}
