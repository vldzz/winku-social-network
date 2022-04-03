package com.example.controller;

import com.example.entity.User;
import com.example.repo.UserRepository;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/auth")
public class AuthController {
    private static final Logger logger = Logger.getLogger(AuthController.class);

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        logger.info(user.toString());

        User userFromDB = userRepository.findByUsername(user.getUsername());
        logger.info(userFromDB == null ? "null" : userFromDB.toString());

        if (userFromDB == null) {
            logger.info("user not found " + Response.Status.NOT_FOUND);
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (userFromDB.getPassword().equals(user.getPassword())) {
            logger.info("Login successful " + Response.Status.OK.getStatusCode());
            return Response.ok("{\"status\": 200, \"answer\": \"Login successful\", \"token\": \"" + userFromDB.getToken() + "\"}")
                    .build();
        } else {
            logger.info("Login failed " + Response.Status.UNAUTHORIZED);
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    @POST
    @Path("/register")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        logger.info(user.toString());


        if (userRepository.findByUsername(user.getUsername()) != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        userRepository.persist(user);

        if (userRepository.isPersistent(user)) {
            return Response.created(URI.create("user/" + user.getId())).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userRepository.findAll().list();
    }


}
