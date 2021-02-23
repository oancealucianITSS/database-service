package com.example.config;

import com.example.entity.Country;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnspportedActions = {HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT,HttpMethod.PATCH};

        //disable HTTP methods for Product, ProductCategory, Country, State : PUT, POST, DELETE
        disableHttpMethods(Product.class, config, theUnspportedActions);
        disableHttpMethods(ProductCategory.class, config, theUnspportedActions);
        disableHttpMethods(Country.class, config, theUnspportedActions);
        disableHttpMethods(State.class, config, theUnspportedActions);

        // call an internal helper method (we are expose ids)
        exposeIds(config);

        //configure cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnspportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnspportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnspportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        //expose entity ids

        // get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // get the entity types for the the entities
        for (EntityType tempEntitytype : entities) {
            entityClasses.add(tempEntitytype.getJavaType());
        }

        //expose the entity id for the array of enity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

}
