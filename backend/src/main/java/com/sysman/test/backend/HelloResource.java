package com.sysman.test.backend;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        System.out.println(" ENDPOINT /hello-world llamado");
        return "Hello, World!";
    }
}