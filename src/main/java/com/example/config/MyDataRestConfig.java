package com.example.config;

import com.example.entity.Product;
import com.example.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] theUnspportedActions = {HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT};

        //disable HTTP methods for Product : PUT, POST, DELETE
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnspportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnspportedActions));

        //disable HTTP methods for ProductCategory : PUT, POST, DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnspportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnspportedActions));


    }
}
