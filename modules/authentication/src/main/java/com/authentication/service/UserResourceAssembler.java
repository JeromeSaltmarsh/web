package com.market.authentication.service;

import com.market.authentication.domain.User;
import com.market.web.UserController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>>{
    @Override
    public Resource<User> toResource(User user) {
        Resource<User> resource = new Resource<User>(user);
        resource.add(linkTo(UserController.class).slash(user.getId()).withSelfRel());
        resource.add(linkTo(UserController.class).slash(user.getId()).slash("entries").withRel("entries"));
        return resource;
    }
}