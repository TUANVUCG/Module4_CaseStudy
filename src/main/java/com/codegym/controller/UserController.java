package com.codegym.controller;

import com.codegym.model.Role;
import com.codegym.model.User;
import com.codegym.repository.IUserRepository;
import com.codegym.service.role.RoleService;
import com.codegym.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        Set<Role> roles = user.getRoles();
        roles.add(roleService.findById(2L).get());
        user.setRoles(roles);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }
}
