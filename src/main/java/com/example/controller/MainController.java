package com.example.controller;

import com.example.entity.User;
import com.example.repo.UserRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.Query;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MainController {
    @Inject
    Template login;

    @Inject
    Template index;

    @Inject
    UserRepository userRepository;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance hello2() {
        return login.instance();
    }

    @GET
    @Path("/home")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance home(String token) {
        User byToken = userRepository.findByToken(token);

        if (token == null || token.isEmpty() || byToken == null) {
            return login.instance();
        }

        index.data("user", byToken);
        return index.instance();
    }


}
