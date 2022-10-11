package com.techelevator.controller;

import com.techelevator.controller.exceptions.NotAuthorizedException;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Beer;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class AdminController {

    private UserDao userDao;

    @Autowired
    public AdminController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/admin/users/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/admin/users/{id}", method = RequestMethod.PUT)
    public boolean updateUserRole(@PathVariable long userId, @Valid @RequestBody User userToChange) throws NotAuthorizedException {
        return userDao.updateUserRole(userId, userToChange);
    }
}
