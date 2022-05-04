package main.java.com.datastax.workshop.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PetClinic Spring Boot Application.
 * 
 * In the following implementation we are using
 * - Java 11 (LTS)
 * - Webflux to expose endpoints over HTTP
 * - Swagger3+ (OpenApi) to provide documentation and test client
 * - DataStax v4 driver with reactive support
 * 
 * The API uses a relational-style objects layer (owner->pet->visit) but the underlying data model
 * follows Cassandra best practices (denormalization). We did not change the API specification 
 * in order to match existing spring-petclinic-angular project.
 * 
 * @author Cedrick Lunven (@clunven)
 */
@SpringBootApplication
public class PetClinicApplication {

    public static void main(String[] args) {
    }
  
}