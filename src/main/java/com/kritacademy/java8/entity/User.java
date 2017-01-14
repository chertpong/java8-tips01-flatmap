package com.kritacademy.java8.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by krit on 1/14/2017.
 */
@Data
public class User {
    private Integer id;
    private String email;
    private Set<Group> groups = new HashSet<Group>();
}
