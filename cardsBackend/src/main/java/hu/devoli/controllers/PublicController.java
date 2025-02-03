package hu.devoli.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;

@Controller("/public")
public class PublicController {

    @Secured("isAnonymous()")
    @Post("/login")
    public String login() {
        return "login";
    }

    @Secured("isAnonymous()")
    @Post("/register")
    public String register() {
        return "register";
    }
}
