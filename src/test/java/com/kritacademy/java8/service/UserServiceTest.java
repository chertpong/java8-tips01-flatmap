package com.kritacademy.java8.service;

import com.kritacademy.java8.entity.Authority;
import com.kritacademy.java8.entity.Group;
import com.kritacademy.java8.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by krit on 1/14/2017.
 */
public class UserServiceTest {
    @Test
    void hasAuthorityNameShouldPass() {
        Authority read = new Authority("READ");
        Authority write = new Authority("WRITE");
        Authority modify = new Authority("MODIFY");
        Authority delete = new Authority("DELETE");
        Authority admin = new Authority("ADMIN");

        Group reader = new Group();
        reader.getAuthorities().add(read);

        Group writer = new Group();
        writer.getAuthorities().addAll(Arrays.asList(read,write,modify));

        Group moderator = new Group();
        moderator.getAuthorities().addAll(Arrays.asList(read,modify,delete));

        Group administrator = new Group();
        administrator.getAuthorities().addAll(Arrays.asList(read,write,modify,delete, admin));

        UserService userService = new UserService();

        User normalUser = new User();
        normalUser.setEmail("normal@normal.com");
        normalUser.getGroups().add(reader);
        assertThat(userService.hasAuthorityName(normalUser, "READ")).isTrue();
        assertThat(userService.hasAuthorityName(normalUser, "read")).isTrue();

        User userWithTwoGroup = new User();
        userWithTwoGroup.setEmail("userWithTwoGroup@userWithTwoGroup.com");
        userWithTwoGroup.getGroups().addAll(Arrays.asList(reader,moderator));
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "READ")).isTrue();
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "read")).isTrue();
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "MODIFY")).isTrue();
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "modify")).isTrue();
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "DELETE")).isTrue();
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "delete")).isTrue();
    }

    @Test
    void hasAuthorityNameShouldFail() {
        Authority read = new Authority("READ");
        Authority write = new Authority("WRITE");
        Authority modify = new Authority("MODIFY");
        Authority delete = new Authority("DELETE");
        Authority admin = new Authority("ADMIN");

        Group reader = new Group();
        reader.getAuthorities().add(read);

        Group writer = new Group();
        writer.getAuthorities().addAll(Arrays.asList(read,write,modify));

        Group moderator = new Group();
        moderator.getAuthorities().addAll(Arrays.asList(read,modify,delete));

        Group administrator = new Group();
        administrator.getAuthorities().addAll(Arrays.asList(read,write,modify,delete, admin));

        User normalUser = new User();
        normalUser.setEmail("normal@normal.com");
        normalUser.getGroups().add(reader);

        UserService userService = new UserService();
        assertThat(userService.hasAuthorityName(normalUser, "WRITE")).isFalse();
        assertThat(userService.hasAuthorityName(normalUser, "write")).isFalse();
        assertThat(userService.hasAuthorityName(normalUser, "ADMIN")).isFalse();
        assertThat(userService.hasAuthorityName(normalUser, "Blahblah")).isFalse();

        User userWithTwoGroup = new User();
        userWithTwoGroup.setEmail("userWithTwoGroup@userWithTwoGroup.com");
        userWithTwoGroup.getGroups().addAll(Arrays.asList(reader,moderator));
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "WRITE")).isFalse();
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "write")).isFalse();
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "ADMIN")).isFalse();
        assertThat(userService.hasAuthorityName(userWithTwoGroup, "Blahblah")).isFalse();
    }

}
