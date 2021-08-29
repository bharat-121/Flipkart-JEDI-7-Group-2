package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {
    // Register controllers
    public ApplicationConfig() {
        register(ProfessorRestAPI.class);
        register(StudentRestAPI.class);
        register(UserRestAPI.class);

    }
}
