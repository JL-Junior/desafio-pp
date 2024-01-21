package org.desafio.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
@ApplicationScoped
public class GreetingsResource {

    @GET
    public String hello(){
        return "Hello from RESTEasy Reactive";
    }
}
