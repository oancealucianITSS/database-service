package com.example.repository;

import com.example.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "countries",path =  "countries")
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
