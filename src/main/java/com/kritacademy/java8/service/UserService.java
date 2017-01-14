package com.kritacademy.java8.service;

import com.kritacademy.java8.entity.Authority;
import com.kritacademy.java8.entity.Group;
import com.kritacademy.java8.entity.User;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by krit on 1/14/2017.
 */
public class UserService {
    public Boolean hasAuthorityName(User user, String name){
        Optional<Authority> targetAuthority = user
                .getGroups()
                .stream()
                .map(Group::getAuthorities)
                .flatMap(Collection::stream)
                .filter(authority -> authority.getName().equalsIgnoreCase(name))
                .findFirst();
        return targetAuthority.isPresent();
    }
}
